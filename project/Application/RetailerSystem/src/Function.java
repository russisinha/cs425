import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


//Please add all of functions in this class


public class Function {

    User user_details = new User();
    static String classname = "oracle.jdbc.driver.OracleDriver";
    static String connection_url = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";
    static String user_sql = "rsinha2";
    static String password_sql = "rsinha2";
    /**
     * Determine if the Email address is valid
     * @param email This is an Email address
     * @return This returns true or false
     */
    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public boolean login_cust(String email, String password) {
        boolean exists = false;
        try {
            String query = "select c.cust_ID, c.email, c.password, ci.f_name, ci.l_name, ci.add_street, ci.add_apt_numb, ci.add_city, ci.add_state, ci.add_zip, ci.add_country, ci.phone from customers c inner join CustomerInfo ci on c.cust_ID = ci.cust_ID where c.email='" + email + "' and c.password='"+password+"'";
            Class.forName(classname);
            Connection conn = DriverManager.getConnection(connection_url, user_sql, password_sql);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                exists = true;
                user_details.id = isNull(rs.getString("cust_id"), "");
                user_details.email = isNull(rs.getString("email"), "");
                user_details.password = isNull(rs.getString("password"), "");
                user_details.f_name = isNull(rs.getString("f_name"), "");
                user_details.l_name = isNull(rs.getString("l_name"), "");
                user_details.street = isNull(rs.getString("add_street"), "");
                user_details.apt_num = isNull(rs.getString("add_apt_numb"), "");
                user_details.city = isNull(rs.getString("add_city"), "");
                user_details.state = isNull(rs.getString("add_state"), "");
                user_details.zip = isNull(rs.getString("add_zip"), "");
                user_details.country = isNull(rs.getString("add_country"), "");
                user_details.phone = isNull(rs.getString("phone"), "");
            }
            st.close();
            conn.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        return exists;
    }

    private String isNull(String val, String newVal){
        if(val == null){
            return newVal;
        }
        else{
            return val;
        }
    }

    public boolean login_emp(String email, String password) {
        boolean exists = false;
        try {
            String query = "select * from employees where email='" + email + "' and password='"+password+"'";
            Class.forName(classname);
            Connection conn = DriverManager.getConnection(connection_url, user_sql, password_sql);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                exists = true;
                user_details.id = rs.getString("emp_id");
                user_details.email = rs.getString("email");
                user_details.password = rs.getString("password");
            }
            st.close();
            conn.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        return exists;
    }

    public boolean isAccountExist(String email, String accounttype) {
        boolean exists = false;
        try {
            String query = "select * from "+accounttype+" where email='" + email + "'";
            Class.forName(classname);
            Connection conn = DriverManager.getConnection(connection_url, user_sql, password_sql);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                exists = true;
            }
            st.close();
            conn.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return exists;
    }

    public boolean createnew_employee(String email, String password) {
        boolean success = false;
        try {
            String query = "insert into employees(email, password) values('"+email+"','"+password+"')" ;
            Class.forName(classname);
            Connection conn = DriverManager.getConnection(connection_url, user_sql, password_sql);
            PreparedStatement st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int affectedRows = st.executeUpdate();
            st.close();
            conn.close();
            success = true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return success;
    }

    public boolean createnew_customer(String email, String password) {
        boolean success = false;
        try {
            String query = "insert into customers(email, password) values('"+email+"','"+password+"')";
            String query2 = "select cust_id from customers where email='"+email+"' and password='"+password+"'";
            Class.forName(classname);
            Connection conn = DriverManager.getConnection(connection_url, user_sql, password_sql);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            ResultSet rs2 = st.executeQuery(query2);
            while(rs2.next()){
                int id = rs2.getInt("cust_id");
                String query3 = "insert into customerinfo(cust_ID) values('"+id+"')";
                ResultSet rs3 = st.executeQuery(query3);
                success = true;
            }

            st.close();
            conn.close();
            success = true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return success;
    }

    public boolean updateEmail_emp(String email) {
        boolean success = false;
        try {
            String query = "update employees set email = '"+email+"' where emp_id="+user_details.id;
            Class.forName(classname);
            Connection conn = DriverManager.getConnection(connection_url, user_sql, password_sql);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            st.close();
            conn.close();
            user_details.email = email;
            success = true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return success;
    }

    public boolean updateEmail_cust(String email) {
        boolean success = false;
        try {
            String query = "update customers set email = '"+email+"' where cust_id="+user_details.id;
            Class.forName(classname);
            Connection conn = DriverManager.getConnection(connection_url, user_sql, password_sql);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            st.close();
            conn.close();
            user_details.email = email;
            success = true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return success;
    }

    public boolean updatePassword_emp(String password) {
        boolean success = false;
        try {
            String query = "update employees set password = '"+password+"' where emp_id="+user_details.id;
            Class.forName(classname);
            Connection conn = DriverManager.getConnection(connection_url, user_sql, password_sql);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            st.close();
            conn.close();
            user_details.password = password;
            success = true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return success;
    }

    public boolean updatePassword_cust(String password) {
        boolean success = false;
        try {
            String query = "update customers set password = '"+password+"' where cust_id="+user_details.id;
            Class.forName(classname);
            Connection conn = DriverManager.getConnection(connection_url, user_sql, password_sql);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            st.close();
            conn.close();
            user_details.password = password;
            success = true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return success;
    }

    public static List getVendorList(){
        List<String> vendors = new ArrayList();
        try {
            String query = "select * from vendors";
            Class.forName(classname);
            Connection conn = DriverManager.getConnection(connection_url, user_sql, password_sql);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vendors.add(rs.getString("vendor_name"));
            }
            st.close();
            conn.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return vendors;
    }

    public static List getStoreList(){
        List<String> stores = new ArrayList();
        try {
            String query = "select store_name from stores";
            Class.forName(classname);
            Connection conn = DriverManager.getConnection(connection_url, user_sql, password_sql);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                stores.add(rs.getString("store_name"));
            }
            st.close();
            conn.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return stores;
    }

    public static List getSizeList(){
        List<String> sizes = new ArrayList();
        try {
            String query = "select size_id from sizes";
            Class.forName(classname);
            Connection conn = DriverManager.getConnection(connection_url, user_sql, password_sql);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                sizes.add(rs.getString("size_id"));
            }
            st.close();
            conn.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return sizes;
    }

    public List getInventory(String search){
        List<Product> inv = new ArrayList();
        try {
            String query = "select p.upc, p.prod_name, p.price, p.brand, p.vendor_name, p.size_id, si.qty, s.store_name from product p inner join StoreInventory si on p.upc = si.upc inner join stores s on s.store_id = si.store_id where p.upc like '%"+search+"%'";
            Class.forName(classname);
            Connection conn = DriverManager.getConnection(connection_url, user_sql, password_sql);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Product p = new Product();
                p.upc = rs.getString("upc");
                p.prodName = rs.getString("prod_name");
                p.price = rs.getFloat("price");
                p.brand = rs.getString("brand");
                p.vendor = rs.getString("vendor_name");
                p.size = rs.getString("size_id");
                p.quantity = rs.getInt("qty");
                p.storeName = rs.getString("store_name");
                inv.add(p);
            }
            st.close();
            conn.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return inv;
    }

    public boolean product_isUPCexist(String upc) {
        boolean exists = false;
        try {
            String query = "select upc from product where upc='"+upc+"'";
            Class.forName(classname);
            Connection conn = DriverManager.getConnection(connection_url, user_sql, password_sql);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                exists = true;
            }
            st.close();
            conn.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return exists;
    }

    public boolean createnew_product(Product prod) {
        boolean success = false;
        try {
            String query = "insert into product(upc, prod_name, price, brand, vendor_name, size_id) values('"+prod.upc+"','"+prod.prodName+"',"+prod.price+",'"+prod.brand+"','"+prod.vendor+"','"+prod.size+"')";
            String query2 = "insert into StoreInventory(store_ID, UPC, qty) values((select store_id from stores where store_name='"+prod.storeName+"'),'"+prod.upc+"',"+prod.quantity+")";
            Class.forName(classname);
            Connection conn = DriverManager.getConnection(connection_url, user_sql, password_sql);
            PreparedStatement st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int affectedRows = st.executeUpdate();
            st.close();
            PreparedStatement st2 = conn.prepareStatement(query2, Statement.RETURN_GENERATED_KEYS);
            int affectedRows2 = st2.executeUpdate();
            st2.close();
            conn.close();
            success = true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return success;
    }

    public Integer checkStoreSpace(String storeName){
        int qty = 0;
        try {
            String query = "select s.store_size-sum(si.qty) as rem from stores s inner join storeinventory si on s.store_id=si.store_id where s.store_name='"+storeName+"' group by s.store_id, s.store_size";
            Class.forName(classname);
            Connection conn = DriverManager.getConnection(connection_url, user_sql, password_sql);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                qty = rs.getInt("rem");
            }
            st.close();
            conn.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return qty;
    }

    public boolean updateQuantity(Product p) {
        boolean success = false;
        try {
            String query = "update StoreInventory set qty = "+p.quantity+" where upc='"+p.upc+"' and store_id=(select store_id from stores where store_name='"+p.storeName+"')";
            Class.forName(classname);
            Connection conn = DriverManager.getConnection(connection_url, user_sql, password_sql);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            st.close();
            conn.close();
            success = true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return success;
    }

    public boolean updateAddress() {
        boolean success = false;
        try {
            String query = "update CustomerInfo set f_name='"+user_details.f_name+"', l_name='"+user_details.l_name+"', add_street='"+user_details.street+"', add_apt_numb='"+user_details.apt_num+"', add_city='"+user_details.city+"', add_state='"+user_details.state+"', add_zip='"+user_details.zip+"', add_country='"+user_details.country+"', phone='"+user_details.phone+"' where cust_id="+user_details.id;
            Class.forName(classname);
            Connection conn = DriverManager.getConnection(connection_url, user_sql, password_sql);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            st.close();
            conn.close();
            success = true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return success;
    }

    public List getCreditCardList(){
        List<CreditCard> cclist = new ArrayList();
        try {
            String query = "select cc_name, cc_numb, cc_exp_date, cc_cvv from Creditcard where cust_id="+user_details.id;
            Class.forName(classname);
            Connection conn = DriverManager.getConnection(connection_url, user_sql, password_sql);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CreditCard cc = new CreditCard();
                cc.cc_name = rs.getString("cc_name");
                cc.cc_numb = rs.getString("cc_numb");
                cc.cc_exp_date = rs.getString("cc_exp_date");
                cc.cc_cvv = rs.getString("cc_cvv");
                cclist.add(cc);
            }
            st.close();
            conn.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return cclist;
    }

    public boolean updateCreditcard(CreditCard newcc, CreditCard oldcc) {
        boolean success = false;
        try {
            String query = "update Creditcard set cc_name='"+newcc.cc_name+"', cc_numb='"+newcc.cc_numb+"', cc_exp_date='"+newcc.cc_exp_date+"', cc_cvv='"+newcc.cc_cvv+"' where cc_name='"+oldcc.cc_name+"' and cc_numb='"+oldcc.cc_numb+"' and cc_exp_date='"+oldcc.cc_exp_date+"' and cc_cvv='"+oldcc.cc_cvv+"'";
            Class.forName(classname);
            Connection conn = DriverManager.getConnection(connection_url, user_sql, password_sql);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            st.close();
            conn.close();
            success = true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return success;
    }

    public boolean addCreditcard(CreditCard newcc) {
        boolean success = false;
        try {
            String query = "insert into Creditcard(cust_id, cc_name, cc_numb, cc_exp_date, cc_cvv) values("+user_details.id+", '"+newcc.cc_name+"', '"+newcc.cc_numb+"', '"+newcc.cc_exp_date+"', '"+newcc.cc_cvv+"')";
            Class.forName(classname);
            Connection conn = DriverManager.getConnection(connection_url, user_sql, password_sql);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            st.close();
            conn.close();
            success = true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return success;
    }

    public boolean deleteCreditcard(CreditCard oldcc) {
        boolean success = false;
        try {
            String query = "delete from Creditcard where cc_name='"+oldcc.cc_name+"' and cc_numb='"+oldcc.cc_numb+"' and cc_exp_date='"+oldcc.cc_exp_date+"' and cc_cvv='"+oldcc.cc_cvv+"'";
            Class.forName(classname);
            Connection conn = DriverManager.getConnection(connection_url, user_sql, password_sql);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            st.close();
            conn.close();
            success = true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return success;
    }

    public List getSearchResults(String searchText){
        List<Product> search = new ArrayList();
        try {
            String query = "select p.upc, p.prod_name, p.price, p.brand, si.qty, s.store_name, s.sales_tax from product p inner join StoreInventory si on p.upc = si.upc inner join stores s on s.store_id = si.store_id where p.prod_name like '%"+searchText+"%'";
            Class.forName(classname);
            Connection conn = DriverManager.getConnection(connection_url, user_sql, password_sql);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Product p = new Product();
                p.upc = rs.getString("upc");
                p.prodName = rs.getString("prod_name");
                p.price = rs.getFloat("price");
                p.tax = rs.getInt("sales_tax");
                p.brand = rs.getString("brand");
                p.quantity = rs.getInt("qty");
                p.storeName = rs.getString("store_name");
                search.add(p);
            }
            st.close();
            conn.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return search;
    }

    public boolean addToCart(String upc, Integer qty) {
        boolean success = false;
        try {
            String query = "select item_qty from CustomerCart where cust_ID='"+user_details.id+"' and UPC='"+upc+"'";
            String query2 = "insert into CustomerCart(cust_ID, UPC, item_qty) values("+user_details.id+", '"+upc+"', '"+qty+"')";
            Class.forName(classname);
            Connection conn = DriverManager.getConnection(connection_url, user_sql, password_sql);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            boolean exists = false;
            while(rs.next()){
                exists = true;
                Integer item_qty = rs.getInt("item_qty");
                Integer qty_new = qty + item_qty;
                String query3 = "update CustomerCart set item_qty="+qty_new+" where cust_ID='"+user_details.id+"' and UPC='"+upc+"'";
                ResultSet rs2 = st.executeQuery(query3);
            }
            if(!exists){
                ResultSet rs2 = st.executeQuery(query2);
            }
            st.close();
            conn.close();
            success = true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return success;
    }

    public List getCartItems(){
        List<Product> cart = new ArrayList();
        try {
            String query = "select c.upc, c.item_qty, p.prod_name, p.price, s.sales_tax, p.brand, p.vendor_name, s.store_id, s.store_name from customercart c inner join product p on c.upc=p.upc inner join StoreInventory si on si.upc=p.upc inner join stores s on s.store_id=si.store_id where c.cust_id="+user_details.id;
            Class.forName(classname);
            Connection conn = DriverManager.getConnection(connection_url, user_sql, password_sql);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Product p = new Product();
                p.upc = rs.getString("upc");
                p.prodName = rs.getString("prod_name");
                p.price = rs.getFloat("price");
                p.tax = rs.getInt("sales_tax");
                p.brand = rs.getString("brand");
                p.quantity = rs.getInt("item_qty");
                p.vendor = rs.getString("vendor_name");
                p.storeName = rs.getString("store_name");
                p.storeId = rs.getInt("store_id");
                cart.add(p);
            }
            st.close();
            conn.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return cart;
    }

    public boolean updateCartProductQuantity(int qty, String upc) {
        boolean success = false;
        try {
            String query = "update CustomerCart set item_qty = "+qty+" where upc='"+upc+"'";
            Class.forName(classname);
            Connection conn = DriverManager.getConnection(connection_url, user_sql, password_sql);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            st.close();
            conn.close();
            success = true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return success;
    }

    public boolean removeFromCart(String upc) {
        boolean success = false;
        try {
            String query = "delete from CustomerCart where upc='"+upc+"'";
            Class.forName(classname);
            Connection conn = DriverManager.getConnection(connection_url, user_sql, password_sql);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            st.close();
            conn.close();
            success = true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return success;
    }

    public List checkQuantityValid(){
        List<Product> prodList = new ArrayList();
        try {
            String query = "select c.cust_id, c.upc, c.item_qty, si.qty from customercart c inner join StoreInventory si on si.upc=c.upc where c.cust_id='"+user_details.id+"' and c.item_qty>si.qty";
            Class.forName(classname);
            Connection conn = DriverManager.getConnection(connection_url, user_sql, password_sql);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Product p = new Product();
                p.upc = rs.getString("upc");
                p.quantity = rs.getInt("qty");
                prodList.add(p);
            }
            st.close();
            conn.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return prodList;
    }

    public boolean placeOrder(){
        boolean success = false;
        int order_id = 0;
        try {
            String query = "select order_id from CustomerOrders order by order_id asc";
            Class.forName(classname);
            Connection conn = DriverManager.getConnection(connection_url, user_sql, password_sql);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                order_id = rs.getInt("order_id");
            }
            order_id++;
            st.close();

            for(int i = 0; i < user_details.cart.size(); i++) {
                Float totalPrice = ((float)user_details.cart.get(i).price + (user_details.cart.get(i).price * ((float)user_details.cart.get(i).tax/(float)100))) * user_details.cart.get(i).quantity;
                totalPrice = Float.parseFloat(String.format("%.2f", totalPrice));
                String query2 = "insert into CustomerOrders(order_ID, cust_ID, UPC, item_qty, amount_paid) values(" + order_id + ", " + user_details.id + ", '"+user_details.cart.get(i).upc+"', "+user_details.cart.get(i).quantity+", "+totalPrice+")";
                String query3 = "update StoreInventory set qty=qty-"+user_details.cart.get(i).quantity+" where upc='"+user_details.cart.get(i).upc+"' and store_id="+user_details.cart.get(i).storeId;
                Statement st2 = conn.createStatement();
                ResultSet rs2 = st2.executeQuery(query2);
                Statement st3 = conn.createStatement();
                ResultSet rs3 = st3.executeQuery(query3);
                st2.close();
            }

            String query4 = "delete from CustomerCart where cust_id="+user_details.id;
            Statement st4 = conn.createStatement();
            ResultSet rs4 = st4.executeQuery(query4);
            st4.close();
            user_details.cart = null;

            conn.close();
            success = true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return success;
    }

    public List getOrderList(){
        List<order> orderList = new ArrayList();
        try {
            String query = "select co.order_id, co.upc, p.prod_name, co.item_qty, co.amount_paid, co.purchase_date from CustomerOrders co inner join product p on p.upc=co.upc where co.cust_id="+user_details.id;
            Class.forName(classname);
            Connection conn = DriverManager.getConnection(connection_url, user_sql, password_sql);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                order o = new order();
                o.orderid = rs.getInt("order_id");
                o.upc = rs.getString("upc");
                o.pName = rs.getString("prod_name");
                o.qty = rs.getInt("item_qty");
                o.amount = rs.getFloat("amount_paid");
                o.date = rs.getString("purchase_date");
                orderList.add(o);
            }
            st.close();
            conn.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return orderList;
    }


}
