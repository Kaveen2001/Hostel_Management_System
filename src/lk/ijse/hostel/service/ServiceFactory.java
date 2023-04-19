package lk.ijse.hostel.service;

import lk.ijse.hostel.service.custom.impl.*;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory() {
    }

    public static ServiceFactory getServiceFactory() {
        return serviceFactory == null ? serviceFactory = new ServiceFactory() : serviceFactory;
    }

    public SuperService getService(ServiceTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentServiceImpl();
            case ROOM:
                return new RoomServiceImpl();
            case RESERVATION:
                return new ReservationServiceImpl();
            case RESERVATION_DETAILS:
                return new ReservationDetailServiceImpl();
            case USER:
                return new UserServiceImpl();
            default:
                return null;
        }
    }
}
