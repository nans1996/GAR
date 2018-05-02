/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.User;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import model.*;
import entity.*;
import java.io.IOException;
import java.io.InputStream;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
/**
 *
 * @author Vasilisa
 */
@ManagedBean(name = "administratorBean")
@RequestScoped
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
    Image image = new Image() ;
    
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
    
    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile uploadedFile = event.getFile();
        image.setName(uploadedFile.getFileName());
        image.setPersonageImageCollection(null);
        image.setType(uploadedFile.getContentType());
        image.setData(uploadedFile.getContents());
        imageFacadeLocal.create(image);
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
 
    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() throws IOException {
        if (file != null) {
            image.setName(file.getFileName());
            image.setPersonageImageCollection(null);
            image.setType(file.getContentType());
            InputStream in = file.getInputstream();
            image.setData(file.getContents());
            imageFacadeLocal.create(image);
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
    }
}
