
# Ruta de la Seda – Simulación en Espiral

Este proyecto implementa una simulación sencilla del problema ICPC J 2024 **SilkRoad** usando programación orientada a objetos en Java. El objetivo es construir un camino que se forma siguiendo un **patrón de espiral cuadrado**, iniciando siempre desde la parte superior de la pantalla. Se buscó practicar conceptos de abstracción, diseño de clases, estructuras de datos y lógica algorítmica.


## Objetivos del proyecto

* Interpretar un problema y transformarlo en un modelo orientado a objetos.
* Crear clases con responsabilidades bien definidas.
* Representar gráficamente un espiral usando una matriz.
* Practicar lógica de movimiento en patrones: derecha → abajo → izquierda → arriba.
* Simular una ruta en consola mediante objetos `Rectangle`.
* Seguir los principios Single responsability y Open and close.


##  Estructura del Proyecto

El proyecto se basa en tres componentes principales:

### **1. Paquete silkRoad**

Contiene la lógica de todo la simulación en clases separadas

### **2. Paquete shapes**

Contiene la lógica de las figuras con las que se ensambla todo el aspecto visual de la simulación.

### **3. Paquete exception**

Además de encargarse del manejo de excepciones, contiene los posibles fallos generados tanto por el propio sistema como por los generados por el usuario

---

## 🧪 Ejecución

Para correr el programa:

Dado que el proyecto se enceuentra en BlueJ deberá instanciar la clase directamente mediante los métodos haciendo click derecho sobre la clase principal, allí podrá simularlo de dos formas; mediante una longitud deseada y a partir de allí agregar robots y tiendas o mediante el input de problema tipo ICPC **new int[][]{{1,2},{2,25,25},{1,20,20},{2,30}}**

De lo contrario el programa solicitará:

```
Ingrese la longitud de la ruta (steps):
```

Mientras más grande el número de `steps`, más grande será el espiral.

---

##  Ejemplo de salida

```
              [][][]
              []  []
              [][][]
```

*(La forma exacta depende del tamaño y de la cantidad de steps.)*

---

##  Conceptos Aprendidos

Durante este proyecto se practicaron:

* Diseño orientado a objetos.
* Abstracción y modelado.
* Uso de matrices como representación gráfica.
* Patrones de movimiento y control de flujo.
* Separación de responsabilidades entre clases.
* Representación visual usando texto.

---

------------------------------------------------------------------------

PROJECT TITLE: SilkRoad
PURPOSE OF PROJECT: Applying concepts OOP 
VERSION: 1.0
Authorss: Hernán David Sánchez, Samuel Steeven Villagrán

