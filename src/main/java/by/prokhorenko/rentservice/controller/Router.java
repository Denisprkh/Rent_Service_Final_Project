package by.prokhorenko.rentservice.controller;

import by.prokhorenko.rentservice.controller.command.PagePath;

public class Router {

    private String page = PagePath.MAIN;
    private DisPathType disPathType = DisPathType.REDIRECT;
    public Router(){

    }

    public Router(String page){
        this.page = page;
    }

    public Router(DisPathType disPathType, String page){
        this.disPathType = disPathType;
        this.page = page;
    }

    public String getPage(){
        return page;
    }

    public void setPage(String page){
        this.page = page;
    }

    public DisPathType getDisPathType(){
        return disPathType;
    }

    public void setForward(){
        this.disPathType = DisPathType.FORWARD;
    }


}
