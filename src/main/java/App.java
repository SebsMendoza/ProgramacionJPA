import database.DataBaseController;

public class App {

    public static void main(String[] args) {
        Empresa emp = Empresa.getInstance();
        DataBaseController dc = DataBaseController.getInstance();

        emp.checkService();
        emp.resetData("resetTables.sql");
        emp.initDataBase();

        emp.Departamentos();
        emp.Programadores();
    }
}
