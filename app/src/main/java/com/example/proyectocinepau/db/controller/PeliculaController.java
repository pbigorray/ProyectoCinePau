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
        addPeliculas("Alcarràs", "120", "Otro verano caluroso y pegajoso en Alcarrás, en la más profunda Cataluña, un pueblo que destaca por su gran cosecha de melocotones. Este año para la familia Solé las cosas serán diferentes. Los integrantes de esta familia descubren que algunos tractores están arrancando los árboles. El patriarca de la familia permanece en silencio. Después de la muerte del terrateniente, el acuerdo que tenían con el antiguo propietario queda desactualizado. Los herederos quieren dejar de alquilarles la tierra a los Solé para venderla a una central de paneles solares. Ante la perdida de sus tierras, todos los miembros de la familia Solé se unirán para sembrar los últimos melocotones. El verano cobrará la perspectiva de los tres hermanos de 16, 13 y 8 años que serán los encargados de animar a la familia ya que las generaciones más mayores serán incapaces de asumir la perdida.\n"
                        , Genero.DRAMA.getGenero(), Edad.EDAD_SUP_12.getEdad(), true, "https://www.ecartelera.com/carteles/15500/15587/001_p.jpg");
        addPeliculas("The Banshees of Inisherin", "108",
                "Pádraic Súilleabháin y Colm Doherty son amigos de toda la vida que conviven, junto con un puñado de habitantes, en una remota isla irlandesa. Su duradera amistad se encuentra en un callejón sin salida cuando Colm decide poner punto y final. de forma inesperada. Es entonces cuando Pádraic pide ayuda a su hermana Siobhán y Dominic , el joven y problemático habitante de la isla, para recomponer y retomar la relación. Más personajes entrarán en juego, Peadar Kearney, el policía local cuya antipatía porPádraic y su hermana se intensifica tras su separación de Colm Pero los repetidos intentos de Pádraic solo sirven para reforzar la determinación de su antiguo amigo y cuando Colm le da un desesperado ultimátum, los acontecimientos evolucionan muy rápidamente y con consecuencias impactantes.\n"
                        , Genero.DRAMA.getGenero(), Edad.EDAD_SUP_16.getEdad(), true, "https://www.ecartelera.com/carteles/17400/17455/001_p.jpg");
        addPeliculas("As bestas", "137",
                "Película dirigida el nominado al Oscar y tres veces ganador del Goya, Rodrigo Sorogoyen ('El Reino', 'Que Dios nos perdone'), y escrita por el mismo junto a Isabel Peña ('Estocolmo'). El título sigue la vida de una pareja francesa de mediana edad que se muda a un pueblo de Galicia en busca de la cercanía y la paz con la naturaleza. Antoine y Olga se encuentran encantados con su nuevo hogar, allí llevan una vida tranquila. Sin embargo, su amor por el pueblo y su entusiasmo por el entorno choca de frente con la opresión abusiva y antagónica de una familia local que ha vivido allí toda su vida, los hermanos Anta. La tensión aumenta hasta el punto de causar una abierta hostilidad y una violencia impactante. La rivalidad entre ambas familias se volverá insostenible llegando a un punto de no retorno.\n"
                        , Genero.TRILLER.getGenero(), Edad.EDAD_SUP_12.getEdad(), false, "https://www.ecartelera.com/carteles/16900/16938/001_p.jpg");
        addPeliculas("Astérix & Obélix: L' empire du milieu", "111", "Año 50 a. C., la dictadura de Dang Sin Kuing asola China tras su Golpe de Estado. Antes de que el vil príncipe se impusiese en el trono reinaba la Emperatriz, ahora encarcelada por el golpista. La princesa Fo Yong, hija de la Emperatriz, tiene una misión: rescatar a su madre de las manos del usurpador. Para cumplir su cometido la princesa contará con la ayuda de su leal escolta Wang Tah. El guardaespaldas no será la única ayuda de la legítima heredera, con motivo del rescate Fo Yong escapa a la Gavia en busca de los dos soldados más atrevidos, fuertes y osados, Astérix y Obélix. Ellos aceptan y desde ese momento comienza una alucinante viaje hacia China plagado de nuevos retos para el grupo de aventureros. Sin embargo, no son ellos los únicos que tienen cometido en el Reino Medio, Julio César y su ejército también se dirigen a China, pero con motivos muy diferentes a los del grupo de rescatadores.\n"
                        , Genero.AVENTURAS.getGenero(), Edad.EDAD_SUP_7.getEdad(), true, "https://www.ecartelera.com/carteles/17500/17595/001_p.jpg");
        addPeliculas("Avatar: The Way of Water", "190", "Sam Worthington y Zoe Saldaña retoman sus papeles de Jake Sully y Neytiri, personajes icónicos de la primera entrega que se han convertido en unos maravillosos padres que hacen todo lo posible por mantener unida a su familia. Cuando acontecimientos imprevistos los alejan de su hogar, los Sully viajan a través de los inmensos confines de la luna Pandora y huyen al territorio que está en poder del clan Metkayina, un pueblo que vive en armonía con los océanos que le rodean. Allí, los Sully deberán aprender a navegar por el peligroso mundo del agua y también a ganarse la aceptación de su nueva comunidad.\n"
                , Genero.AVENTURAS.getGenero(), Edad.EDAD_SUP_12.getEdad(), true, "https://www.ecartelera.com/carteles/4200/4261/004_p.jpg");
        addPeliculas("Babylon", "183",
                "'Babylon' explora la ascensión y caída de grandes figuras en los inicios de Hollywood, una época llena de desmedidas ambiciones y excesos. Jack Conrad (Brad Pitt), un actor que haría cualquier cosa para llegar a donde quiere estar, conoce a Nellie LaRoy (Margot Robbie), una exactriz que decide ayudarle a comenzar en la industria, logrando que pronto se convierta en una estrella. Sin embargo, el ego y la ambición de Conrad harán que empiece a tener delirios de grandeza y de poder, sumergiéndose en el alcohol, las drogas y las relaciones superfluas, lo que le hace distanciarse de sus seres queridos y de su trabajo. Todo cambia cuando conoce a una mujer que hará que quiera volver a encauzar su vida y su carrera artística, pero no será tan fácil como cree.\n"
                , Genero.COMEDIA.getGenero(), Edad.EDAD_SUP_16.getEdad(), true, "https://www.ecartelera.com/carteles/16000/16025/001_p.jpg");
    }


}
