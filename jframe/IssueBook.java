/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Ujjwal
 */
public class IssueBook extends javax.swing.JFrame {

    /**
     * Creates new form IssueBook
     */
    public IssueBook() {
        initComponents();
    }
    // to fetch the book details from the database and display it to book_details panel
    
    public void getBookDetails(){
       int bookid = Integer.parseInt(txt_bookid.getText());
       try{
           Connection con =DBConnection.getConnection();
           String sql = "select * from book_details where Book_id = ?";
           PreparedStatement pst = con.prepareStatement(sql);
           pst.setInt(1, bookid);
           ResultSet rs = pst.executeQuery();
           
           if(rs.next()){
               lbl_bkid.setText(rs.getString("Book_id"));
               lbl_bkname.setText(rs.getString("Book_name")); 
               lbl_author.setText(rs.getString("author"));
               lbl_quantity.setText(rs.getString("quantity"));
               bkerror.setText("");
           }else{
               bkerror.setText("Invaid book id");
           }
           
       }catch(Exception e){
           e.printStackTrace();
       }
    }
    
     // to fetch the student details from the database and display it to student_details panel
    
    public void getStudentDetails(){
       int Student_id = Integer.parseInt(txt_studentid.getText());
       try{
           Connection con =DBConnection.getConnection();
           String sql = "select * from student_details where StudentId = ?";
           PreparedStatement pst = con.prepareStatement(sql);
           pst.setInt(1, Student_id);
           ResultSet rs = pst.executeQuery();
           
           if(rs.next()){
               lbl_stdid.setText(rs.getString("StudentId"));
               lbl_stdname.setText(rs.getString("name")); 
               lbl_course.setText(rs.getString("course"));
               lbl_branch.setText(rs.getString("branch"));
               stderror.setText("");
           }else{
               stderror.setText("Invaid student id");
           }
           
       }catch(Exception e){
           e.printStackTrace();
       }
    }
    
    //insert issue_book_details to database
    public boolean issueBook(){
        boolean isissued = false;
        int bookId = Integer.parseInt(lbl_bkid.getText());
        int studentId = Integer.parseInt(lbl_stdid.getText());
        String bookname = lbl_bkname.getText();
        String studentname = lbl_stdname.getText();
        
        Date uIssueDate = Issue_date.getDatoFecha();
        Date uDueDate = Due_date.getDatoFecha();
        
        long l1 = uIssueDate.getTime();
        long l2 = uDueDate.getTime();
        
        java.sql.Date sIssueDate = new java.sql.Date(l1); 
        java.sql.Date sDueDate = new java.sql.Date(l2); 
        
        try{
            Connection con =DBConnection.getConnection();
           String sql = "insert into issue_book_details(book_id,book_name,Student_id,student_name,"
                   +"issue_date,due_date,status) values(?,?,?,?,?,?,?)";
           PreparedStatement pst = con.prepareStatement(sql);
           pst.setInt(1, bookId);
           pst.setString(2,bookname);
           pst.setInt(3, studentId );
           pst.setString(4, studentname);
           pst.setDate(5,sIssueDate);
           pst.setDate(6,sDueDate);
           pst.setString(7,"pending");
           
           int rowcount =  pst.executeUpdate();
           
           if(rowcount > 0){
               isissued = true;
           }else{
               isissued = false;
           }
           
        }catch(Exception e){
            e.printStackTrace();
        }
        return isissued;
    }
    
    //updating book count 
    public void updateBook(){
        int bookId = Integer.parseInt(lbl_bkid.getText());
        try{
            Connection con =DBConnection.getConnection();
            String sql = "Update book_details set quantity = quantity-1 where Book_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1,bookId);
            
            int rowcount = pst.executeUpdate();
            
            if(rowcount > 0){
                JOptionPane.showMessageDialog(this,"Book count updated");
                int initialcount = Integer.parseInt(lbl_quantity.getText());
                lbl_quantity.setText(Integer.toString(initialcount- 1));
            }else{
                JOptionPane.showMessageDialog(this,"Can't update book count");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //checking whether book already allocated or not 
    public boolean isAlreadyIssued(){
        boolean isAlreadyIssue = false;
        int bookId = Integer.parseInt(lbl_bkid.getText());
        int studentId = Integer.parseInt(lbl_stdid.getText()); 
        
        try{
            Connection con =DBConnection.getConnection();
            String sql = "select * from issue_book_details where Book_id = ? and StudentId = ? and status = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            pst.setInt(2, studentId);
            pst.setString(3, "pending");
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                isAlreadyIssue = true;
            }else{
                isAlreadyIssue = false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return isAlreadyIssue;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_main = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        bkerror = new javax.swing.JLabel();
        lbl_quantity = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_bkid = new javax.swing.JLabel();
        lbl_bkname = new javax.swing.JLabel();
        lbl_author = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        stderror = new javax.swing.JLabel();
        lbl_branch = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbl_stdid = new javax.swing.JLabel();
        lbl_stdname = new javax.swing.JLabel();
        lbl_course = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_bookid = new app.bolivia.swing.JCTextField();
        txt_studentid = new app.bolivia.swing.JCTextField();
        jLabel10 = new javax.swing.JLabel();
        Due_date = new rojeru_san.componentes.RSDateChooser();
        jLabel11 = new javax.swing.JLabel();
        Issue_date = new rojeru_san.componentes.RSDateChooser();
        jLabel18 = new javax.swing.JLabel();
        rSMaterialButtonCircle2 = new rojerusan.RSMaterialButtonCircle();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_main.setBackground(new java.awt.Color(255, 255, 255));
        panel_main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons (1)/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel2.setText("BACK");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 50));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons (1)/AddNewBookIcons/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel1.setText(" Book Details");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 260, 100));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 290, 5));

        bkerror.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        bkerror.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(bkerror, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 610, 280, 30));

        lbl_quantity.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        lbl_quantity.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 510, 180, 30));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Book Name :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 120, 30));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Author :");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 100, 30));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Book Id :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 90, 30));

        lbl_bkid.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        lbl_bkid.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_bkid, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, 160, 30));

        lbl_bkname.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        lbl_bkname.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_bkname, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 370, 170, 30));

        lbl_author.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        lbl_author.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 440, 170, 30));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Quantity :");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, 100, 30));

        panel_main.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 790));

        jPanel4.setBackground(new java.awt.Color(102, 102, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 290, 5));

        stderror.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        stderror.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(stderror, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 610, 260, 30));

        lbl_branch.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        lbl_branch.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_branch, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 510, 180, 30));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Student Name :");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 130, 30));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Course :");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 100, 30));

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Student Id :");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 100, 30));

        lbl_stdid.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        lbl_stdid.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_stdid, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, 160, 30));

        lbl_stdname.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        lbl_stdname.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_stdname, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, 150, 30));

        lbl_course.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        lbl_course.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 440, 170, 30));

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons (1)/AddNewBookIcons/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel14.setText(" Student Details");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 280, 100));

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Branch :");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, 100, 30));

        panel_main.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 360, 790));

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 51, 51));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons (1)/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel12.setText(" Issue Book");
        panel_main.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 120, 200, -1));

        jPanel5.setBackground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panel_main.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 190, 310, 5));

        jPanel7.setBackground(new java.awt.Color(102, 102, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("X");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panel_main.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 0, 71, 40));

        jLabel9.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 51, 51));
        jLabel9.setText("Book Id :");
        panel_main.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 290, 90, -1));

        txt_bookid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        txt_bookid.setPlaceholder("Enter Book Id");
        txt_bookid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookidFocusLost(evt);
            }
        });
        txt_bookid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bookidActionPerformed(evt);
            }
        });
        panel_main.add(txt_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 280, 300, -1));

        txt_studentid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        txt_studentid.setPlaceholder("Enter Student Id");
        txt_studentid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentidFocusLost(evt);
            }
        });
        txt_studentid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_studentidActionPerformed(evt);
            }
        });
        panel_main.add(txt_studentid, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 360, 300, -1));

        jLabel10.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 51, 51));
        jLabel10.setText("Due Date :");
        panel_main.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 550, 110, -1));

        Due_date.setColorBackground(new java.awt.Color(255, 51, 51));
        Due_date.setColorForeground(new java.awt.Color(255, 51, 51));
        Due_date.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        Due_date.setPlaceholder("Select Due Date");
        panel_main.add(Due_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 540, 290, -1));

        jLabel11.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 51, 51));
        jLabel11.setText("Student Id :");
        panel_main.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 370, 110, -1));

        Issue_date.setColorBackground(new java.awt.Color(255, 51, 51));
        Issue_date.setColorForeground(new java.awt.Color(255, 51, 51));
        Issue_date.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        Issue_date.setPlaceholder("Select Issue Date");
        panel_main.add(Issue_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 460, 290, -1));

        jLabel18.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 51, 51));
        jLabel18.setText("Issue Date :");
        panel_main.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 470, 110, -1));

        rSMaterialButtonCircle2.setBackground(new java.awt.Color(255, 51, 51));
        rSMaterialButtonCircle2.setText("ISSUE BOOK");
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });
        panel_main.add(rSMaterialButtonCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 640, 340, 80));

        getContentPane().add(panel_main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 790));

        setSize(new java.awt.Dimension(1301, 790));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        HomePage home = new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void txt_bookidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookidFocusLost
        // TODO add your handling code here:
        if(!txt_bookid.getText().equals("")){
            getBookDetails();
        }
        
    }//GEN-LAST:event_txt_bookidFocusLost

    private void txt_bookidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookidActionPerformed

    private void txt_studentidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentidFocusLost
        // TODO add your handling code here:
        if(!txt_studentid.getText().equals("")){
            getStudentDetails();
        }
    }//GEN-LAST:event_txt_studentidFocusLost

    private void txt_studentidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_studentidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_studentidActionPerformed

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
        // TODO add your handling code here:
        if(lbl_quantity.getText().equals("0") ){
            JOptionPane.showMessageDialog(this,"Book is not available");
        }else{
            if(isAlreadyIssued() == false){
        if(issueBook() == true){
            JOptionPane.showMessageDialog(this,"Book issued successfully");
            updateBook();
        }else{
            JOptionPane.showMessageDialog(this,"Can't issue the book");  
        }
        }
        }
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IssueBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.componentes.RSDateChooser Due_date;
    private rojeru_san.componentes.RSDateChooser Issue_date;
    private javax.swing.JLabel bkerror;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lbl_author;
    private javax.swing.JLabel lbl_bkid;
    private javax.swing.JLabel lbl_bkname;
    private javax.swing.JLabel lbl_branch;
    private javax.swing.JLabel lbl_course;
    private javax.swing.JLabel lbl_quantity;
    private javax.swing.JLabel lbl_stdid;
    private javax.swing.JLabel lbl_stdname;
    private javax.swing.JPanel panel_main;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private javax.swing.JLabel stderror;
    private app.bolivia.swing.JCTextField txt_bookid;
    private app.bolivia.swing.JCTextField txt_studentid;
    // End of variables declaration//GEN-END:variables
}
