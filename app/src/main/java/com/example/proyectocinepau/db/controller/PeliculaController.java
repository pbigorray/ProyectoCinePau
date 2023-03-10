package com.example.proyectocinepau.db.controller;

import android.content.Context;

import com.example.proyectocinepau.db.DataBase;
import com.example.proyectocinepau.model.Pelicula;
import com.example.proyectocinepau.model.tipos.Edad;
import com.example.proyectocinepau.model.tipos.Genero;

import java.util.List;

import io.realm.Realm;

public class PeliculaController {
    private Context context;
    private Realm con;

    public PeliculaController(Context context) {
        this.context = context;
        this.con = DataBase.getInstance().connection(context);
        long pelicuals = con.where(Pelicula.class).count();
        if (pelicuals == 0) {
            addDefault();
        }
    }

    public List<Pelicula> getOnCartelera(){
        return con.where(Pelicula.class).equalTo("cartelera",true).findAll();
    }
    public List<Pelicula> getCartelera(){
        return con.where(Pelicula.class).findAll();
    }
    public int getSize(){
        return (int) con.where(Pelicula.class).count();
    }
    public int getSizeCaratelera(){
        return (int) con.where(Pelicula.class).equalTo("cartelera",true).count();
    }
    public Pelicula getOnPelicula(int i){
        return getOnCartelera().get(i);
    }
    public Pelicula getPelicula(int i){
        return getCartelera().get(i);
    }
    public Pelicula getPelicula(String titulo){
        return con.where(Pelicula.class).equalTo("titulo",titulo).findFirst();
    }

    public void updatePelicula(Pelicula pelicula){
        con.beginTransaction();
        con.copyToRealmOrUpdate(pelicula);
        con.commitTransaction();
    }

    public void addPeliculas(String titulo, String duracion, String descripcion, String genero, String edad, boolean cartelera, String url) {
        Pelicula p = new Pelicula();

        p.setTitulo(titulo);
        p.setDuracion(duracion);
        p.setDescripcion(descripcion);
        p.setGenero(genero);
        p.setEdad(edad);
        p.setCartelera(cartelera);
        p.setUrlImagen(url);

        con.beginTransaction();
        con.copyToRealmOrUpdate(p);
        con.commitTransaction();
    }
    public void addDefault() {
        addPeliculas("Alcarr??s", "120", "Otro verano caluroso y pegajoso en Alcarr??s, en la m??s profunda Catalu??a, un pueblo que destaca por su gran cosecha de melocotones. Este a??o para la familia Sol?? las cosas ser??n diferentes. Los integrantes de esta familia descubren que algunos tractores est??n arrancando los ??rboles. El patriarca de la familia permanece en silencio. Despu??s de la muerte del terrateniente, el acuerdo que ten??an con el antiguo propietario queda desactualizado. Los herederos quieren dejar de alquilarles la tierra a los Sol?? para venderla a una central de paneles solares. Ante la perdida de sus tierras, todos los miembros de la familia Sol?? se unir??n para sembrar los ??ltimos melocotones. El verano cobrar?? la perspectiva de los tres hermanos de 16, 13 y 8 a??os que ser??n los encargados de animar a la familia ya que las generaciones m??s mayores ser??n incapaces de asumir la perdida.\n"
                        , Genero.DRAMA.getGenero(), Edad.EDAD_SUP_12.getEdad(), true, "https://www.ecartelera.com/carteles/15500/15587/001_p.jpg");
        addPeliculas("The Banshees of Inisherin", "108",
                "P??draic S??illeabh??in y Colm Doherty son amigos de toda la vida que conviven, junto con un pu??ado de habitantes, en una remota isla irlandesa. Su duradera amistad se encuentra en un callej??n sin salida cuando Colm decide poner punto y final. de forma inesperada. Es entonces cuando P??draic pide ayuda a su hermana Siobh??n y Dominic , el joven y problem??tico habitante de la isla, para recomponer y retomar la relaci??n. M??s personajes entrar??n en juego, Peadar Kearney, el polic??a local cuya antipat??a porP??draic y su hermana se intensifica tras su separaci??n de Colm Pero los repetidos intentos de P??draic solo sirven para reforzar la determinaci??n de su antiguo amigo y cuando Colm le da un desesperado ultim??tum, los acontecimientos evolucionan muy r??pidamente y con consecuencias impactantes.\n"
                        , Genero.DRAMA.getGenero(), Edad.EDAD_SUP_16.getEdad(), true, "https://www.ecartelera.com/carteles/17400/17455/001_p.jpg");
        addPeliculas("As bestas", "137",
                "Pel??cula dirigida el nominado al Oscar y tres veces ganador del Goya, Rodrigo Sorogoyen ('El Reino', 'Que Dios nos perdone'), y escrita por el mismo junto a Isabel Pe??a ('Estocolmo'). El t??tulo sigue la vida de una pareja francesa de mediana edad que se muda a un pueblo de Galicia en busca de la cercan??a y la paz con la naturaleza. Antoine y Olga se encuentran encantados con su nuevo hogar, all?? llevan una vida tranquila. Sin embargo, su amor por el pueblo y su entusiasmo por el entorno choca de frente con la opresi??n abusiva y antag??nica de una familia local que ha vivido all?? toda su vida, los hermanos Anta. La tensi??n aumenta hasta el punto de causar una abierta hostilidad y una violencia impactante. La rivalidad entre ambas familias se volver?? insostenible llegando a un punto de no retorno.\n"
                        , Genero.TRILLER.getGenero(), Edad.EDAD_SUP_12.getEdad(), false, "https://www.ecartelera.com/carteles/16900/16938/001_p.jpg");
        addPeliculas("Ast??rix & Ob??lix: L' empire du milieu", "111", "A??o 50 a. C., la dictadura de Dang Sin Kuing asola China tras su Golpe de Estado. Antes de que el vil pr??ncipe se impusiese en el trono reinaba la Emperatriz, ahora encarcelada por el golpista. La princesa Fo Yong, hija de la Emperatriz, tiene una misi??n: rescatar a su madre de las manos del usurpador. Para cumplir su cometido la princesa contar?? con la ayuda de su leal escolta Wang Tah. El guardaespaldas no ser?? la ??nica ayuda de la leg??tima heredera, con motivo del rescate Fo Yong escapa a la Gavia en busca de los dos soldados m??s atrevidos, fuertes y osados, Ast??rix y Ob??lix. Ellos aceptan y desde ese momento comienza una alucinante viaje hacia China plagado de nuevos retos para el grupo de aventureros. Sin embargo, no son ellos los ??nicos que tienen cometido en el Reino Medio, Julio C??sar y su ej??rcito tambi??n se dirigen a China, pero con motivos muy diferentes a los del grupo de rescatadores.\n"
                        , Genero.AVENTURAS.getGenero(), Edad.EDAD_SUP_7.getEdad(), true, "https://www.ecartelera.com/carteles/17500/17595/001_p.jpg");
        addPeliculas("Avatar: The Way of Water", "190", "Sam Worthington y Zoe Salda??a retoman sus papeles de Jake Sully y Neytiri, personajes ic??nicos de la primera entrega que se han convertido en unos maravillosos padres que hacen todo lo posible por mantener unida a su familia. Cuando acontecimientos imprevistos los alejan de su hogar, los Sully viajan a trav??s de los inmensos confines de la luna Pandora y huyen al territorio que est?? en poder del clan Metkayina, un pueblo que vive en armon??a con los oc??anos que le rodean. All??, los Sully deber??n aprender a navegar por el peligroso mundo del agua y tambi??n a ganarse la aceptaci??n de su nueva comunidad.\n"
                , Genero.AVENTURAS.getGenero(), Edad.EDAD_SUP_12.getEdad(), true, "https://www.ecartelera.com/carteles/4200/4261/004_p.jpg");
        addPeliculas("Babylon", "183",
                "'Babylon' explora la ascensi??n y ca??da de grandes figuras en los inicios de Hollywood, una ??poca llena de desmedidas ambiciones y excesos. Jack Conrad (Brad Pitt), un actor que har??a cualquier cosa para llegar a donde quiere estar, conoce a Nellie LaRoy (Margot Robbie), una exactriz que decide ayudarle a comenzar en la industria, logrando que pronto se convierta en una estrella. Sin embargo, el ego y la ambici??n de Conrad har??n que empiece a tener delirios de grandeza y de poder, sumergi??ndose en el alcohol, las drogas y las relaciones superfluas, lo que le hace distanciarse de sus seres queridos y de su trabajo. Todo cambia cuando conoce a una mujer que har?? que quiera volver a encauzar su vida y su carrera art??stica, pero no ser?? tan f??cil como cree.\n"
                , Genero.COMEDIA.getGenero(), Edad.EDAD_SUP_16.getEdad(), true, "https://www.ecartelera.com/carteles/16000/16025/001_p.jpg");
    }


}
