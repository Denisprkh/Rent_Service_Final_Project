package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.builder.*;
import by.prokhorenko.rentservice.controller.command.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.*;
import by.prokhorenko.rentservice.entity.Advertisement;
import by.prokhorenko.rentservice.entity.AdvertisementDataHandler;
import by.prokhorenko.rentservice.entity.Flat;
import by.prokhorenko.rentservice.entity.FlatAddress;
import by.prokhorenko.rentservice.entity.FlatDescription;
import by.prokhorenko.rentservice.entity.FlatPhoto;
import by.prokhorenko.rentservice.entity.User;
import by.prokhorenko.rentservice.exception.DaoException;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.AdvertisementService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UpdateAdvertisementCommand implements Command {

    private static final Logger LOG = LogManager.getLogger();
    private AdvertisementService advertisementService;

    public UpdateAdvertisementCommand() {
        this.advertisementService = ServiceFactory.getInstance().getAdvertisementService();
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        User author = (User) request.getSession().getAttribute(Attribute.USER);
        Advertisement advertisement = (Advertisement) session.getAttribute(Attribute.ADVERTISEMENT);
        int advertisementId = advertisement.getId();
        int flatAddressId = advertisement.getFlat().getFlatAddress().getId();
        int flatDescriptionId = advertisement.getFlat().getFlatDescription().getId();
        int flatsId = advertisement.getFlat().getId();
        try {
            List<InputStream> flatPhotoData = buildPhotosDataFromRequest(request);
            List<FlatPhoto> flatPhotos = buildFlatPhotosFromDataList(flatPhotoData, flatsId);
            AdvertisementDataHandler dataHandler = buildAdvertisementDataHandlerFromRequest(request);
            advertisementService.defineIncorrectData(dataHandler);
            FlatAddress flatAddress = buildFlatAddressFromDataHandler(dataHandler, flatAddressId);
            FlatDescription flatDescription = buildFlatDescriptionFromDataHandler(dataHandler, flatDescriptionId);
            Flat flat = buildFlat(flatDescription, flatAddress, flatPhotos, flatsId);
            Advertisement updatedAdvertisement = buildAdvertisementFromDataHandler(advertisementId, author, dataHandler, flat);
            advertisementService.updateAdvertisement(updatedAdvertisement);
            String redirectUrl = buildRedirectUrl(request, CommandName.MY_ADS_PAGE.getCommandName());
            router.setPage(redirectUrl);
        } catch (ServiceException e) {
            LOG.error(e);
            if (e.getCause() instanceof DaoException) {
                router.setForward();
                router.setPage(PagePath.SERVER_ERROR_PAGE);
            } else {
                String redirectUrl = buildRedirectUrl(request, CommandName.UPDATE_ADVERTISEMENT_PAGE.getCommandName());
                router.setPage(redirectUrl);
                session.setAttribute(Attribute.UPDATE_AN_ADVERTISEMENT_ERROR_MESSAGE, e.getMessage());
            }
        }
        return router;
    }

    private List<InputStream> buildPhotosDataFromRequest(HttpServletRequest request) {
        List<InputStream> photosData = new ArrayList<>();
        try {
            Part firstImgData = request.getPart(RequestParameter.FIRST_IMG_PART);
            Part secondImgData = request.getPart(RequestParameter.SECOND_IMG_PART);
            Part thirdImgData = request.getPart(RequestParameter.THIRD_IMG_PART);
            photosData.add(firstImgData.getInputStream());
            photosData.add(secondImgData.getInputStream());
            photosData.add(thirdImgData.getInputStream());
        } catch (IOException | ServletException e) {
            request.getSession().setAttribute(Attribute.ADD_AN_ADVERTISEMENT_ERROR_MESSAGE,
                    ResourceBundleMessageKey.ADVERTISEMENT_INVALID_IMG_AMOUNT);
        }
        return photosData;
    }

    private AdvertisementDataHandler buildAdvertisementDataHandlerFromRequest(HttpServletRequest request) {
        AdvertisementDataHandler dataHandler = new AdvertisementDataHandlerBuilder()
                .buildTitle(request.getParameter(RequestParameter.ADVERTISEMENT_TITLE))
                .buildPrice(request.getParameter(RequestParameter.ADVERTISEMENT_PRICE))
                .buildCity(request.getParameter(RequestParameter.ADVERTISEMENT_FLAT_LOCATION_CITY))
                .buildDistrict(request.getParameter(RequestParameter.ADVERTISEMENT_FLAT_LOCATION_DISTRICT))
                .buildStreet(request.getParameter(RequestParameter.ADVERTISEMENT_FLAT_LOCATION_STREET))
                .buildHouseNumber(request.getParameter(RequestParameter.ADVERTISEMENT_FLAT_LOCATION_HOUSE))
                .buildRooms(request.getParameter(RequestParameter.ADVERTISEMENT_FLAT_DESCRIPTION_ROOMS))
                .buildLivingArea(request.getParameter(RequestParameter.ADVERTISEMENT_FLAT_DESCRIPTION_AREA))
                .buildIsHasFurniture(Boolean.parseBoolean(request.getParameter
                        (RequestParameter.ADVERTISEMENT_FLAT_DESCRIPTION_FURNITURE)))
                .buildIsHasHomeAppliances(Boolean.parseBoolean(request.getParameter
                        (RequestParameter.ADVERTISEMENT_FLAT_DESCRIPTION_HOME_APPLIANCES)))
                .buildIsHasTheInternet(Boolean.parseBoolean(request.getParameter
                        (RequestParameter.ADVERTISEMENT_FLAT_DESCRIPTION_INTERNET)))
                .buildPossibleWithChildren(Boolean.parseBoolean(request.getParameter
                        (RequestParameter.ADVERTISEMENT_FLAT_DESCRIPTION_CHILDREN)))
                .buildPossibleWithPets(Boolean.parseBoolean(request.getParameter
                        (RequestParameter.ADVERTISEMENT_FLAT_DESCRIPTION_PETS)))
                .buildUsersDescription(request.getParameter
                        (RequestParameter.ADVERTISEMENT_FLAT_DESCRIPTION_USERS_DESCRIPTION))
                .buildAdvertisementDataHandler();

        return dataHandler;
    }

    private List<FlatPhoto> buildFlatPhotosFromDataList(List<InputStream> data, int flatsId) {
        List<FlatPhoto> flatPhotos = new ArrayList<>();
        for (InputStream inputStream : data) {
            FlatPhoto flatPhoto = new FlatPhoto();
            flatPhoto.setFlatPhotoData(inputStream);
            flatPhoto.setFlatsId(flatsId);
            flatPhotos.add(flatPhoto);
        }
        return flatPhotos;
    }

    private FlatAddress buildFlatAddressFromDataHandler(AdvertisementDataHandler handler, int flatAddressId) {
        FlatAddress flatAddress = new FlatAddressBuilder()
                .buildId(flatAddressId)
                .buildCity(handler.getCity())
                .buildDistrict(handler.getDistrict())
                .buildStreet(handler.getStreet())
                .buildHouse(handler.getHouseNumber())
                .buildFlatAddress();
        return flatAddress;
    }

    private FlatDescription buildFlatDescriptionFromDataHandler(AdvertisementDataHandler handler, int flatDescriptionId) {
        FlatDescription flatDescription = new FlatDescriptionBuilder()
                .buildId(flatDescriptionId)
                .buildRooms(Integer.parseInt(handler.getRooms()))
                .buildLivingArea(Float.parseFloat(handler.getArea()))
                .buildHasFurniture(handler.isHasFurniture())
                .buildPossibleWithChild(handler.isPossibleWithChildren())
                .buildHasHomeAppliances(handler.isHasHomeAppliances())
                .buildPossibleWithPets(handler.isPossibleWithPets())
                .buildHasTheInternet(handler.isHasTheInternet())
                .buildUsersDescription(handler.getUsersDescription())
                .buildFlatDescription();
        return flatDescription;
    }

    private Flat buildFlat(FlatDescription flatDescription, FlatAddress flatAddress, List<FlatPhoto> flatPhotos,
                           int flatsId) {
        Flat flat = new FlatBuilder()
                .buildId(flatsId)
                .buildFlatAddress(flatAddress)
                .buildFlatDescription(flatDescription)
                .buildFlatPhotos(flatPhotos)
                .buildFlat();
        return flat;
    }

    private Advertisement buildAdvertisementFromDataHandler(int id, User author, AdvertisementDataHandler handler, Flat flat) {
        Advertisement advertisement = new AdvertisementBuilder()
                .buildId(id)
                .buildPrice(new BigDecimal(handler.getPrice()))
                .buildTitle(handler.getTitle())
                .buildAuthor(author)
                .buildFlat(flat)
                .buildAdvertisement();
        return advertisement;
    }
}
