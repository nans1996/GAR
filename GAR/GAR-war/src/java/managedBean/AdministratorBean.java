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
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.apache.log4j.Logger;
/**
 *
 * @author Vasilisa
 */
@ManagedBean(name = "administratorBean")
@SessionScoped
public class AdministratorBean {
    
    public AdministratorBean() throws IOException {
        
    }
    static final Logger LOGGER = Logger.getLogger(AdministratorBean.class);

    private User user = new User();
    
    @EJB
    private UserFacadeLocal userFacade;
   
    @EJB
    ImageFacadeLocal imageFacadeLocal;
    Image image = new Image();
    @EJB
    PersonageImageFacadeLocal personageImageFacadeLocal;
    @EJB
    PersonageFacadeLocal personageFacadeLocal;
    Personage personage = new Personage();
    @EJB
    private GoalFacadeLocal goalFacadeLocal;
    private Goal goal = new Goal();
    private PersonageImage personageImage = new PersonageImage();
    private String quantityLevel;
    @EJB
    private ClientFacadeLocal clientFacadeLocal;
//    Администрирование пользователей
    public List<User> getAllUser() {
        List<User> users = userFacade.findAll();
        LOGGER.info("Выведен список пользователей");
        LOGGER.debug("This is debug");
        LOGGER.fatal("This is fatal : ");
        //logger.info("Выведен список пользователей");
        return users;
    }

    public String getQuantityLevel() {
        return quantityLevel;
    }

    public void setQuantityLevel(String quantityLevel) {
        this.quantityLevel = quantityLevel;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public PersonageImage getPersonageImage() {
        return personageImage;
    }

    public void setPersonageImage(PersonageImage personageImage) {
        this.personageImage = personageImage;
    }

    public Personage getPersonage() {
        return personage;
    }

    public void setPersonage(Personage personage) {
        this.personage = personage;
    }
    
    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }
    
    //создать  
    public String createUser() { //?
        this.userFacade.create(this.getUser());
        //после добавления перебрасывает на index
        return "index";
    }
    
    //удалить
    public void deleteUser(User user) { //?
        this.userFacade.remove(user);
    }

    //обновить 
    public String editUser(User user) { //?
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
        Image img = imageFacadeLocal.find(1);
        return new DefaultStreamedContent(new ByteArrayInputStream(img.getData()), img.getType());
    }

    public StreamedContent getImageCreate() throws IOException {
        if (file != null) {
            return new DefaultStreamedContent(file.getInputstream(), file.getContentType());
        }
        return null;
    }
        
    //читаем картиночку из потока в байтовый массив
    public byte[] InputStreamToArryByte(InputStream in) throws IOException {
        byte[] imageInByte;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            BufferedImage bImageFromConvert = ImageIO.read(in);
            ImageIO.write(bImageFromConvert, "jpg", baos);
            imageInByte = baos.toByteArray();
        }
        //logger.info("Произведено чтение картинки за потока в байтовый формат");
        return imageInByte;
    }
    public void upload() throws IOException {
        if (file != null) {
            image.setName(file.getFileName());
            image.setType(file.getContentType());
            image.setPersonageImage(null);
            image.setData(InputStreamToArryByte(file.getInputstream()));
            imageFacadeLocal.create(image);
            personage = personageFacadeLocal.findPersonageByName(personage.getName());
            personageImage.setIDImage(image);
            personageImage.setIDPersonage(personage);
            personageImage.setLevel(1);
            personageImageFacadeLocal.create(personageImage);
            FacesMessage message = new FacesMessage("Добавлен файл:", file.getFileName());
            FacesContext.getCurrentInstance().addMessage(null, message);   
            //logger.info("Довавнение картинки на уровень персонажу");
        } else {
            //logger.severe("Error file = null");
        }
        
    }
    public List<Personage> personagesFild() {
        return personageFacadeLocal.findAll();
    }
    public List<String> levelFild() {
        List<String> results = new ArrayList<String>();
        for (int i = 1; i < 22; i++) {
            results.add(""+i);
        }
        return results;
    }
    
    //Назначить бан/снять бан
    public String ban() { 
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        int id = Integer.parseInt(params.get("id"));
        Client client = clientFacadeLocal.find(id);
        client.setBan(!client.getBan());
        clientFacadeLocal.edit(client);
        //
        return "/user?faces-redirect=true";
    }
}
