package model;


import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Producto implements Serializable {

    private int id;
    @SerializedName("title")
    private String nombre;
    @SerializedName("description")
    private String descripcion;
    @SerializedName("stock")
    private int cantidad;
    @SerializedName("price")
    private double precio;


}
