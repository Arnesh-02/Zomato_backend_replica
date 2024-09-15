package models;

public class Customer_details{
    private int id;
    private String name;
    private String username;
    private String password;
    private String dob;
    private String email;
    private String phone_no;
    private String gender;
    private String address;
    private String category;
    
    public void setId(int id){
      this.id=id;
    }
    
    
    public void setName(String name){
        this.name=name;
    }
    
    public void  setUsername(String username){
        this.username=username;
    }
    	
    public void setPassword(String password) {
    	this.password=password;
    }
    
    public String getPassword() {
    	return password;
    }
  
    
    public void setDob(String dob){
        this.dob=dob;
    }
    
    public void setEmail(String email){
        this.email=email;
    }
    
    public void setPhone_no(String phone_no){
        this.phone_no=phone_no;
    }
    
    public void  setGender(String gender){
        this.gender=gender;
    }
    
    public void setAddress(String address){
        this.address=address;
    }
    
    public void setCategory(String category){
        this.category=category;
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }


    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getCategory() {
        return category;
    }

}

