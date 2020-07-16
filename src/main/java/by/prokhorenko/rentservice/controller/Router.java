package by.prokhorenko.rentservice.controller;

public class Router {
    enum DisPathType {
        FORWARD,
        REDIRECT
    }

    private String page = PagePath.INDEX;
    private DisPathType disPathType = DisPathType.FORWARD;

    public String getPage(){
        return page;
    }

    public void setPage(String page){
        this.page = page;
    }

    public DisPathType getDisPathType(){
        return disPathType;
    }

    public void setRedirect(){
        this.disPathType = DisPathType.REDIRECT;
    }

    public void setForward(){
        this.disPathType = DisPathType.FORWARD;
    }


}
