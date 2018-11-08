/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.prestamo.Controlador;

import ec.edu.espe.arquitectura.prestamo.Entidades.Cliente;
import ec.edu.espe.arquitectura.prestamo.Entidades.Tabla_Amortizacion;
import ec.edu.espe.arquitectura.prestamo.Entidades.Total;
import ec.edu.espe.arquitectura.prestamo.Modelo.Bean_NuevoPrestamoLocal;
import ec.edu.espe.arquitectura.prestamo.util.FacesUtil;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Steven
 */
@ManagedBean
@SessionScoped
public class NuevoPrestamoBean implements Serializable {

    private String cedula;
    private String tipo;
    private double monto;
    private int plazo;
    private ArrayList<Tabla_Amortizacion> amortizacion = new ArrayList<Tabla_Amortizacion>();
    private ArrayList<Total> lista_total = new ArrayList<Total>();
    @EJB
    Bean_NuevoPrestamoLocal bean_nuevoPrestamo;

    Cliente cli = new Cliente();

    public ArrayList<Total> getLista_total() {
        return lista_total;
    }

    public void setLista_total(ArrayList<Total> lista_total) {
        this.lista_total = lista_total;
    }

    public NuevoPrestamoBean() {
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public ArrayList<Tabla_Amortizacion> getAmortizacion() {
        return amortizacion;
    }

    public void setAmortizacion(ArrayList<Tabla_Amortizacion> amortizacion) {
        this.amortizacion = amortizacion;
    }

    public String aceptar() {
        cli = bean_nuevoPrestamo.verificarCliente(cedula);
        amortizacion.clear();
        if (cli != null) {
            if (bean_nuevoPrestamo.verificarTipoPrestamoCliente(cedula, tipo)) {
                if (bean_nuevoPrestamo.validarMonto(tipo, monto)) {
                    if (bean_nuevoPrestamo.validarPlazo(tipo, plazo)) {
                        CargarTabla();
                        return "DetallePrestamo";
                    } else {
                        FacesUtil.addMessageWarn(null, bean_nuevoPrestamo.mensajePlazo(tipo));
                        return "";
                    }
                } else {
                    FacesUtil.addMessageWarn(null, bean_nuevoPrestamo.mensajeMonto(tipo));
                    return "";
                }
            } else {
                if (tipo.equals("Comercial")) {
                    FacesUtil.addMessageWarn(null, "Únicamente los clientes jurídicos pueden acceder a prétamos comerciales");
                } else {
                    FacesUtil.addMessageWarn(null, "Los clientes jurídicos pueden acceder únicamente a préstamos comerciales");
                }
                return "";
            }
        } else {
            FacesUtil.addMessageWarn(null, "El cliente no existe");
            return "";
        }

    }

    public void CargarTabla() {
        Tabla_Amortizacion ta;
        double interes_anual = 16.06;
        double interes_mensual = interes_anual / 12 / 100;
        double interes = 0;
        double valor_cuota = monto * (interes_mensual * Math.pow(1 + interes_mensual, plazo)) / (Math.pow(interes_mensual + 1, plazo) - 1);
        double capital = 0;
        double saldo = monto;
        List<String> lista_fecha = bean_nuevoPrestamo.GenerarFechas(plazo);
        for (int i = 0; i <= plazo; i++) {

            if (i == 0) {
                ta = new Tabla_Amortizacion(i, 0, 0, 0, bean_nuevoPrestamo.Convertir(saldo), lista_fecha.get(i), "");
            } else {
                interes = saldo * ((16.06 / 12) / 100);
                capital = valor_cuota - interes;
                saldo = saldo - capital;
                ta = new Tabla_Amortizacion(i, bean_nuevoPrestamo.Convertir(capital), bean_nuevoPrestamo.Convertir(interes), bean_nuevoPrestamo.Convertir(capital + interes), bean_nuevoPrestamo.Convertir(saldo), lista_fecha.get(i), "Pendiente");
            }
            amortizacion.add(ta);
        }
        Total tot = new Total("Total", bean_nuevoPrestamo.Convertir(monto), bean_nuevoPrestamo.Convertir(monto * interes_anual / 100), bean_nuevoPrestamo.Convertir(valor_cuota * plazo), bean_nuevoPrestamo.Convertir(saldo), "", "");
        lista_total.add(tot);
    }
    public String aceptarDetalle()
    {
        return "Inicio";
    }
}