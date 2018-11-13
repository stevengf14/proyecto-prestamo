/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Controlador;

import ec.edu.espe.arquitectura.prestamo.Entidades.Amortizacion;
import ec.edu.espe.arquitectura.prestamo.Entidades.Cliente;
import ec.edu.espe.arquitectura.prestamo.Entidades.Prestamo;
import ec.edu.espe.arquitectura.prestamo.Entidades.Tabla_Amortizacion;
import ec.edu.espe.arquitectura.prestamo.Entidades.Total;
import ec.edu.espe.arquitectura.prestamo.Modelo.Bean_NuevoPrestamoLocal;

import ec.edu.espe.arquitectura.prestamo.Modelo.PagoPrestamoFacadeLocal;
import ec.edu.espe.arquitectura.prestamo.util.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;


/**
 *
 * @author Samsung-PC
 */
@ManagedBean
@SessionScoped
public class PagosBean implements Serializable {

    
    @EJB
    PagoPrestamoFacadeLocal bean_pagos;
    @EJB
    Bean_NuevoPrestamoLocal bean_nuevoPrestamo;
    Cliente cli = new Cliente();
    List<Amortizacion> amortbusqueda = new ArrayList<Amortizacion>();
    List<Amortizacion> listaAmort = new ArrayList<Amortizacion>();
    private double monto;
    private int plazo;
    private ArrayList<Total> lista_total = new ArrayList<>();
    private String nombre="";
    private double valorCuota;
    private double xCobrar;
    private double valorRecibido;
    private double cambio;
    private double cargo;
    Amortizacion amorti = new Amortizacion();
    private ArrayList<Tabla_Amortizacion> amortizacion = new ArrayList<Tabla_Amortizacion>();
    private ArrayList<Amortizacion> listAmortiza = new ArrayList<Amortizacion>();
    public List<Amortizacion> getListaAmort() {
        return listaAmort;
    }

    public void setListaAmort(List<Amortizacion> listaAmort) {
        this.listaAmort = listaAmort;
    }

    public Amortizacion getAmorti() {
        return amorti;
    }

    public ArrayList<Amortizacion> getListAmortiza() {
        return listAmortiza;
    }

    public void setListAmortiza(ArrayList<Amortizacion> listAmortiza) {
        this.listAmortiza = listAmortiza;
    }

    
    
    public void setAmorti(Amortizacion amorti) {
        this.amorti = amorti;
    }
    

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    private double total;

    public List<Amortizacion> getAmortbusqueda() {
        return amortbusqueda;
    }
    
    
    public void setAmortbusqueda(List<Amortizacion> amortbusqueda) {
        this.amortbusqueda = amortbusqueda;
    }
    public double getCargo() {
        return cargo;
    }

    public void setCargo(double cargo) {
        this.cargo = cargo;
    }
     Tabla_Amortizacion ta;
     private Amortizacion taSelected;

    public Amortizacion getTaSelected() {
        return taSelected;
    }

    public void setTaSelected(Amortizacion taSelected) {
        this.taSelected = taSelected;
    }

     
    public Tabla_Amortizacion getTa() {
        return ta;
    }

    public void setTa(Tabla_Amortizacion ta) {
        this.ta = ta;
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public double getValorCuota() {
        return valorCuota;
    }

    public void setValorCuota(double valorCuota) {
        this.valorCuota = valorCuota;
    }

    public double getxCobrar() {
        return xCobrar;
    }

    public void setxCobrar(double xCobrar) {
        this.xCobrar = xCobrar;
    }

    public double getValorRecibido() {
        return valorRecibido;
    }

    public void setValorRecibido(double valorRecibido) {
        this.valorRecibido = valorRecibido;
    }

    public double getCambio() {
        return cambio;
    }

    public void setCambio(double cambio) {
        this.cambio = cambio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    private String cedula = "";
    
    Prestamo pres= new Prestamo();

    public Prestamo getPres() {
        return pres;
    }

    public void setPres(Prestamo pres) {
        this.pres = pres;
    }
 

    public ArrayList<Tabla_Amortizacion> getAmortizacion() {
        return amortizacion;
    }

    public void setAmortizacion(ArrayList<Tabla_Amortizacion> amortizacion) {
        this.amortizacion = amortizacion;
    }

    public Bean_NuevoPrestamoLocal getBean_nuevoPrestamo() {
        return bean_nuevoPrestamo;
    }

    public void setBean_nuevoPrestamo(Bean_NuevoPrestamoLocal bean_nuevoPrestamo) {
        this.bean_nuevoPrestamo = bean_nuevoPrestamo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public ArrayList<Total> getLista_total() {
        return lista_total;
    }

    public void setLista_total(ArrayList<Total> lista_total) {
        this.lista_total = lista_total;
    }

    public PagoPrestamoFacadeLocal getBean_pagos() {
        return bean_pagos;
    }

    public void setBean_pagos(PagoPrestamoFacadeLocal bean_pagos) {
        this.bean_pagos = bean_pagos;
    }
   
  
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

  
    
   
    /**
     * Creates a new instance of PagosBean
     */
    public PagosBean() {
        
    }
  
   
//    public void seleccionFila(SelectEvent event) {
//        System.out.println(event.);
////        System.out.println(((Tabla_Amortizacion) event.getObject()).getNumero());
//        amortizacion.get(1).getValor_cuota();
//    }
//    
//     public void onRowUnselect(UnselectEvent event) {
//         valorCuota=((Tabla_Amortizacion) event.getObject()).getCapital();
//    }
 
    
   
    
 public void cargTabla() {
         cli = bean_nuevoPrestamo.verificarCliente(cedula);
            if (cli != null) {
                
        System.out.println(bean_pagos.busquedaMonto(cedula));
        System.out.println(bean_pagos.busquedaPlazo(cedula));
        nombre=bean_pagos.busquedaNombre(cedula);
        double interes_anual = 16.06;
        double interes_mensual = interes_anual / 12 / 100;
        double interes = 0;
        double valor_cuota = bean_pagos.busquedaMonto(cedula) * (interes_mensual * Math.pow(1 + interes_mensual, bean_pagos.busquedaPlazo(cedula))) / (Math.pow(interes_mensual + 1, bean_pagos.busquedaPlazo(cedula)) - 1);
        double capital = 0;
        double saldo = bean_pagos.busquedaMonto(cedula);
        List<String> lista_fecha = bean_pagos.GenerarFechas(bean_pagos.busquedaPlazo(cedula),bean_pagos.busquedaFecha(cedula));
        for (int i = 1; i <= bean_pagos.busquedaPlazo(cedula); i++) {

            if (i == 0) {
                ta = new Tabla_Amortizacion(i, 0, 0, 0, bean_nuevoPrestamo.Convertir(saldo), lista_fecha.get(i), "");
            } else {
                interes = saldo * ((16.06 / 12) / 100);
                capital = valor_cuota - interes;
                saldo = saldo - capital;
                ta = new Tabla_Amortizacion(i, bean_nuevoPrestamo.Convertir(capital), bean_nuevoPrestamo.Convertir(interes), bean_nuevoPrestamo.Convertir(capital + interes), bean_nuevoPrestamo.Convertir(saldo), lista_fecha.get(i), bean_pagos.CompararFechas(lista_fecha.get(i)));
            }
            amortizacion.add(ta);
        }
        } else {
            FacesUtil.addMessageWarn(null, "El cliente no existe");
            
        }
        CalculosPago();
        
//        Total tot = new Total("Total", bean_pagos.busquedaMonto(cedula), bean_pagos.busquedaMonto(cedula) * interes_anual / 100, valor_cuota * bean_pagos.busquedaPlazo(cedula), saldo, "", "");
       // lista_total.add(tot);
    
    
 }
 
    public void CalculosPago(){
         for (Amortizacion amort: amortbusqueda) {
            if(amort.getEstado().equals("Pendiente")||amort.getEstado().equals("Mora")){
                taSelected=amort;
                if(taSelected.getEstado().equals("Mora")){
                    cargo=6.38;
                }else{
                    cargo=0;
                }
                total=cargo+taSelected.getValorCuota().doubleValue();
                break;
            }
                       
        }
        
    }
    
    public void RegistrarPago(){
        
        
    }
    
     public void cargarTabla() {
         cli = bean_nuevoPrestamo.verificarCliente(cedula);
         if (cli != null) {
             nombre=bean_pagos.busquedaNombre(cedula);
             amortbusqueda =bean_pagos.busquedaAmortizacion(cedula);
//             System.out.println(amortbusqueda.get(0).getClass().toString());
         
        } else {
            FacesUtil.addMessageWarn(null, "El cliente no existe");
            
        }
        CalculosPago();
        
//        Total tot = new Total("Total", bean_pagos.busquedaMonto(cedula), bean_pagos.busquedaMonto(cedula) * interes_anual / 100, valor_cuota * bean_pagos.busquedaPlazo(cedula), saldo, "", "");
       // lista_total.add(tot);
    
    
 }
 
 
    
    
}
