package cairu.controller;

import cairu.model.Emprestimo;
import cairu.jsf.util.JsfUtil;
import cairu.jsf.util.JsfUtil.PersistAction;
import cairu.bean.EmprestimoFacade;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.context.RequestContext;

@Named("emprestimoController")
@SessionScoped
public class EmprestimoController implements Serializable {
    
    @EJB
    private cairu.bean.EmprestimoFacade ejbFacade;
    private List<Emprestimo> items = null;
    private Emprestimo selected;
    
    public EmprestimoController() {
    }
    
    public void AbrirDialogo() {
        Map<String, Object> opcoes = new HashMap();
        RequestContext.getCurrentInstance().openDialog("FindUsuario", opcoes, null);
    }
    
    public Emprestimo getSelected() {
        return selected;
    }
    
    public void setSelected(Emprestimo selected) {
        this.selected = selected;
    }
    
    protected void setEmbeddableKeys() {
    }
    
    protected void initializeEmbeddableKey() {
    }
    
    private EmprestimoFacade getFacade() {
        return ejbFacade;
    }
    
    public Emprestimo prepareCreate() {
        selected = new Emprestimo();
        initializeEmbeddableKey();
        return selected;
    }
    
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EmprestimoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("EmprestimoUpdated"));
    }
    
    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("EmprestimoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public List<Emprestimo> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }
    
    public Emprestimo getEmprestimo(java.lang.Integer id) {
        return getFacade().find(id);
    }
    
    public List<Emprestimo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }
    
    public List<Emprestimo> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    @FacesConverter(forClass = Emprestimo.class)
    public static class EmprestimoControllerConverter implements Converter {
        
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EmprestimoController controller = (EmprestimoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "emprestimoController");
            return controller.getEmprestimo(getKey(value));
        }
        
        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }
        
        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }
        
        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Emprestimo) {
                Emprestimo o = (Emprestimo) object;
                return getStringKey(o.getIdEmprestimo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Emprestimo.class.getName()});
                return null;
            }
        }
        
    }
    
}
