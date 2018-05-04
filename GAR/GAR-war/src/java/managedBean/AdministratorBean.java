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
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.activation.MimetypesFileTypeMap;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
/**
 *
 * @author Vasilisa
 */
@ManagedBean(name = "administratorBean")
@SessionScoped
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

    private StreamedContent content;

    public StreamedContent getContent() {
        return content;
    }

    public void setContent(StreamedContent content) {
        this.content = content;
    }

    @PostConstruct
    public void getSavedImage(){
        Image img = imageFacadeLocal.find(1);
        content = new DefaultStreamedContent(new ByteArrayInputStream(img.getData()), img.getType());
    }

    
    public void upload() throws IOException {
        if (file != null) {
            image.setName(file.getFileName());
            image.setType(file.getContentType());
            image.setPersonageImageCollection(null);
            InputStream in = file.getInputstream();
            byte[] fileContents = new byte[in.available()];
            for (int i = 0; i < in.available(); i++) {
                fileContents[i] = (byte) in.read();
            }
            image.setData(fileContents);
            imageFacadeLocal.create(image);
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
    }
}
