/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.User;
import java.util.List;
import javax.ejb.EJB;
import model.*;
import entity.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
/**
 *
 * @author Vasilisa
 */
@ManagedBean(name = "administratorBean")
public class AdministratorBean {
    public AdministratorBean() {
        //user = new User();
    }

    @EJB
    private UserFacadeLocal userFacade;
    private User user = new User();
    
    @EJB
    UserFacadeLocal userFacadeLocal;
    @EJB
    ImageFacadeLocal imageFacadeLocal;
    Image image = new Image();
    
//    Администрирование пользователей
    public List<User> getAllUser() {
        return  userFacadeLocal.findAll();
    }
    
    //создать  

    public String createUser() {
        this.userFacade.create(this.getUser());
        //после добавления перебрасывает на index
        return "index";
    }
    
    //удалить
    public void deleteUser(User user) {
        this.userFacade.remove(user);
    }

    //обновить 
    public String editUser(User user) {
        this.setUser(user);
        return "edit";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
 
    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public StreamedContent getSavedImage(){
        Image img = imageFacadeLocal.find(3);
        return new DefaultStreamedContent(new ByteArrayInputStream(img.getData()), img.getType());
    }

    
    public void upload() throws IOException {
        if (file != null) {
            image.setName(file.getFileName());
            image.setType(file.getContentType());
            image.setPersonageImageCollection(null);
            byte[] imageInByte;
            InputStream in = file.getInputstream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedImage bImageFromConvert = ImageIO.read(in);
            ImageIO.write(bImageFromConvert, "jpg", baos);
            imageInByte = baos.toByteArray();
            baos.close();
            image.setData(imageInByte);
            imageFacadeLocal.create(image);
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

//    public void handleFileUpload(FileUploadEvent event) throws IOException {
//        UploadedFile uploadedFile = event.getFile();
//        image.setName(uploadedFile.getFileName());
//        image.setType(uploadedFile.getContentType());
//        InputStream in = uploadedFile.getInputstream();
//        byte[] mass = new byte[in.available()];
//        for (int i = 0; i < in.available(); i++) {
//            mass[i] = (byte) in.read();
//        }
//        image.setData(mass);
//        imageFacadeLocal.create(image);
//        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
//        FacesContext.getCurrentInstance().addMessage(null, message);
//
//    } 
         

}
