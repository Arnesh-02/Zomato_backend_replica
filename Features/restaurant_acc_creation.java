class restaurant_acc_creation {
    public void restaurant_acc_creation() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Restaurant Name: ");
        String res_name = scanner.nextLine();

        System.out.print("Enter Star Rating: ");
        int res_star_rating = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Address: ");
        String res_address = scanner.nextLine();

        System.out.print("Enter Cuisines: ");
        String res_cusines = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String res_phone_no = scanner.nextLine();

        System.out.print("Enter Opening Hours: ");
        String res_opening_hours = scanner.nextLine();

        System.out.print("Enter Average Delivery Time: ");
        String res_avg_delivery_time = scanner.nextLine();

        System.out.print("Enter Average Distance in Kms: ");
        float res_avg_distance_in_kms = scanner.nextFloat();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Menu ID: ");
        int res_menu_id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Category: ");
        String res_category = scanner.nextLine();

        System.out.print("Enter Reviews ID: ");
        int res_reviews_id = scanner.nextInt();

        String sql = "INSERT INTO restaurent (Name, Star_rating, Address, Cusines, Phone_no, Opening_hours, Avg_delivery_time, Avg_distance_in_kms, Menu_id, Category, Reviews_id, Created_at, Modified_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = dbconnection.getconnection();
            pstm = con.prepareStatement(sql);

            pstm.setString(1, res_name);
            pstm.setInt(2, res_star_rating);
            pstm.setString(3, res_address);
            pstm.setString(4, res_cusines);
            pstm.setString(5, res_phone_no);
            pstm.setString(6, res_opening_hours);
            pstm.setString(7, res_avg_delivery_time);
            pstm.setFloat(8, res_avg_distance_in_kms);
            pstm.setInt(9, res_menu_id);
            pstm.setString(10, res_category);
            pstm.setInt(11, res_reviews_id);
            pstm.setTimestamp(12, new Timestamp(new Date().getTime()));
            pstm.setTimestamp(13, new Timestamp(new Date().getTime()));
            pstm.executeUpdate();
            System.out.println("Restaurant account created successfully!!");
            pstm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}