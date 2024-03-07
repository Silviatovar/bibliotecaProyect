/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.entidades;

import java.io.Serializable;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.OneToMany;


/**
 *
 * @author Silvia Tovar
 */
@Entity
public class Libro implements Serializable {
  @Id
    @Column(name = "ISBN")
    private String isbn;
    @Column(length = 255, nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String fechaEdicion;
    // Generamos la tabla intermedia de la relacion con nombre rel_libro_autor
    @JoinTable(
            name = "rel_Libro_autor",
            joinColumns = @JoinColumn(name = "FK_LIBRO_A", nullable = false), 
            inverseJoinColumns = @JoinColumn(name = "FK_AUTOR", nullable = false))
    // Le indicamos que tiene una relacion mucho a mucho con autor
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Autor> autores;
    @JoinTable(
            name = "rel_libro_genero",
            joinColumns = @JoinColumn(name = "FK_LIBRO_G", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "FK_GENERO", nullable = false))
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Genero> generos;
    @Column(nullable = false)
    private String imagen;
    @Column(nullable = false)
    private Long cantidad;
    @OneToMany(mappedBy = "libro") // "libro" es el nombre del atributo en la entidad Prestamo que representa esta relación
    private List<Prestamo> prestamos;

    @Override
    public String toString() {
        return "Libro{" + "isbn=" + isbn + ", titulo=" + titulo + ", fechaEdicion=" + fechaEdicion + ", autores=" + autores + ", generos=" + generos + ", imagen=" + imagen + ", cantidad=" + cantidad + ", prestamos=" + prestamos + '}';
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechaEdicion() {
        return fechaEdicion;
    }

    public void setFechaEdicion(String fechaEdicion) {
        this.fechaEdicion = fechaEdicion;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
}