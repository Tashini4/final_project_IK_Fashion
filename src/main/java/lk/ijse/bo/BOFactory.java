package lk.ijse.bo;

import lk.ijse.bo.SuperBO;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {

    }

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;

    }
    public enum BoType{
        CUSTOMER,EMPLOYEE,ITEM,INVENTORY,REGISTER,SALARY,SUPPLIER,ORDERDETAIL
    }
    public SuperBO getBo(BoType boType){
        switch (boType){
            case CUSTOMER:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case ITEM:
                return new ItemBoImpl();
            case INVENTORY:
                return new InventoryBoImpl();
            case REGISTER:
                return new RegisterBOImpl();
            case SALARY:
                return new SalaryBOImpl();
            case SUPPLIER:
                return new SalaryBOImpl();
            case ORDERDETAIL:
                return new OrderDetailBOImpl();
            default:
                return null;
        }
    }

}
