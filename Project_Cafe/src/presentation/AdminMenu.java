package presentation;

import bussiness.entity.User;
import bussiness.imploy.UserImp;
import confic.Notify;
import confic.Validation;

import java.util.Scanner;

public class AdminMenu {
    public static UserImp userImp = new UserImp();

    public static void UserMenu() {
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println(Notify.MENU_USER);
            System.out.print(Notify.NOTI_YOUR_CHOISE);
            String pick = sc.nextLine();
            boolean checkPick = Validation.checkInterger(pick);
            if (checkPick) {
                switch (Integer.parseInt(pick)) {
                    case 1:
                        AdminMenu.listUser();
                        break;
                    case 2:
                        AdminMenu.creatUser(sc);
                        break;
                    case 3:
                        AdminMenu.updateUser(sc);
                        break;
                    case 4:
                        AdminMenu.findUser(sc);
                        break;
                    case 5:
                        AdminMenu.transPassword(Login.usaname);
                        break;
                    case 6:
                        CafeManagement.CafeManagent();
                        break;
                }
            } else {
                System.out.println(Notify.NOTI_PICK_1_6);
            }
        } while (true);
    }

    public static void listUser() {
        userImp.displayByDate();
    }

    public static void creatUser(Scanner sc) {
        User user = userImp.inputData(sc);
        boolean result = userImp.creat(user);
        if (result) {
            System.out.println(Notify.NOTI_CREAT_SUCCESSFUL);
        } else {
        }
    }

    public static void updateUser(Scanner sc) {
        userImp.displayByDate();
        System.out.println("\n" + Notify.NOTI_INPUT_ID_UPDATE);
        do {
            String ID = sc.nextLine();
            boolean check = Validation.checkEnter(ID);
            if (!check) {
                boolean checkInt = Validation.checkInterger(ID);
                if (checkInt) {
                    if (userImp.findByID(Integer.parseInt(ID)) != null) {
                        boolean result = userImp.update(userImp.updateStatus(userImp.findByID(Integer.parseInt(ID))));
                        if (result) {
                            System.out.println(Notify.NOTI_UP_DATE_SUCCESSFUL);
                            break;
                        } else {
                            System.out.println(Notify.NOTI_UP_DATE_FAIL);
                            break;
                        }
                    } else {
                        System.out.println(Notify.NOTI_INPUT_ID);
                    }
                } else {
                    System.out.println(Notify.NOTI_INPUT_ID);
                }
            } else {
                System.out.println(Notify.NOTI_INPUT_INTERGER);
            }
        } while (true);
    }

    public static void findUser(Scanner sc) {
        System.out.print(" Nh????p t??n ta??i khoa??n ho????c t??n ng??????i du??ng ba??n mu????n ti??m : ");
        do {
            String search = sc.nextLine();
            boolean checkSearch = Validation.checkEnter(search);
            if (!checkSearch) {
                if (!userImp.findByName(search).isEmpty()) {
                    System.out.println(Notify.NOTI_DISPLAY_USER);
                    for (User us : userImp.findByName(search)) {
                        String statusUser = " Block ";
                        if (us.isUserStatus()) {
                            statusUser = " Active ";
                        }
                        System.out.printf(Notify.FONT_USER,
                                us.getUserID(), us.getUserName(), us.getFullName(), us.getDateRegister(), statusUser, us.getEmail(), us.getPhoneNumber());

                    }
                    System.out.println(Notify.POINT_USer);
                } else {
                    System.out.println(Notify.NOTI_UNKNOW_DATA);
                }
                break;
            } else {
                System.out.println(Notify.NOTI_UNKNOW_DATA + "\n");
                break;
            }
        } while (true);
    }

    public static void transPassword(String userName) {
        User userNameFind = userImp.findByNamePass(userName);
        Scanner sc = new Scanner(System.in);
        String password;
        do {
            System.out.println(" Nh????p m????t kh????u cu?? ?????? ??????i m????t kh????u : ");
            String oldPassword = sc.nextLine();
            if(userNameFind.getPassword().equals(oldPassword)){
                break;
            }else {
                System.out.println(" M????t kh????u cu?? ch??a ??u??ng !");
            }
        }while (true);
        do {
            System.out.println(" Nh????p va??o m????t kh????u m????i !");
            password = sc.nextLine();
            if (Validation.checkLenght(password)) {
                userNameFind.setPassword(password);
                break;
            } else {
                System.out.println(" Vui lo??ng nh????p m????t kh????u da??i h??n 6 ki?? t???? !");
            }
        }  while (true);
        do {
            System.out.println(" Nh????p xa??c nh????n m????t kh????u m????i : ");
            String confirm = sc.nextLine();
            if (Validation.checkConfirmPassword(password,confirm)){
                userNameFind.setPassword(confirm);
                break;
            }else {
                System.out.println(" M????t kh????u ch??a kh????p ! Ha??y th???? la??i !");
            }
        }while (true);
        userImp.update(userNameFind);
        System.out.println(" ??????i m????t kh????u tha??nh c??ng !");
    }
}



