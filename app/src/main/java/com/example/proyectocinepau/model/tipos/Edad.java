package com.example.proyectocinepau.model.tipos;

public enum Edad {
        A("Todas las edades"),AI("Todas las edades,recomendado para niños"),EDAD_INF_7("Mayores de 7 años"),EDAD_SUP_7("Mayores de 7 años. Recomendado para niños"),EDAD_SUP_12("Mayores de 12 años"),
        EDAD_SUP_16("Mayores de 16 años"),EDAD_SUP_18("Mayores de 18 años");

        private String  edad;

        Edad(String edad) {
                this.edad=edad;
        }

        public String  getEdad() {
                return edad;
        }
}
