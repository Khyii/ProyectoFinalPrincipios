
package uis.edu.proyectoBackend.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import uis.edu.proyectoBackend.modelo.Guide;
import uis.edu.proyectoBackend.modelo.User;
import uis.edu.proyectoBackend.repositorio.IGuideRepository;
import uis.edu.proyectoBackend.repositorio.IUserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final IUserRepository userRepository;
    private final IGuideRepository guideRepository;

    private final Random random = new Random();

    private static final String[] NAMES = {
        "Carlos", "Andres", "Maria", "Laura", "Daniel",
        "Sofia", "Juan", "Valentina", "Sebastian", "Camila",
        "David", "Isabella", "Miguel", "Lucia", "Alejandro"
    };

    private static final String[] LASTNAMES = {
        "Rodriguez", "Gomez", "Martinez", "Lopez", "Garcia",
        "Perez", "Sanchez", "Ramirez", "Torres", "Diaz",
        "Moreno", "Castro", "Rojas", "Herrera", "Vargas"
    };

    private static final String[] TOPICS = {
        "Programacion",
        "Bases de datos",
        "Redes",
        "Seguridad",
        "Java",
        "Python",
        "Docker",
        "Spring Boot",
        "Desarrollo web",
        "Linux",
        "Git",
        "Inteligencia artificial",
        "Hardware",
        "Software",
        "Cloud computing"
    };

    private static final String[] TITLE_PREFIXES = {
        "Introduccion a ",
        "Guia completa de ",
        "Aprende a utilizar ",
        "Conceptos fundamentales de ",
        "Consejos para trabajar con ",
        "Como implementar ",
        "Buenas practicas en ",
        "Tutorial practico de ",
        "Configuracion inicial de ",
        "Errores comunes en ",
        "Optimizacion de ",
        "Todo lo que debes saber sobre "
    };

    public DataInitializer(
            IUserRepository userRepository,
            IGuideRepository guideRepository) {

        this.userRepository = userRepository;
        this.guideRepository = guideRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {

        // Avoid generating the initial dataset more than once.
        if (userRepository.count() > 0 || guideRepository.count() > 0) {
            System.out.println("Initial data already exists.");
            return;
        }

        System.out.println("Generating 1000 users...");

        List<User> users = generateUsers();
        userRepository.saveAll(users);

        System.out.println("Generating 9000 guides...");

        List<Guide> guides = generateGuides(users);
        guideRepository.saveAll(guides);

        System.out.println("Initial data generated successfully.");
    }

    private List<User> generateUsers() {

        List<User> users = new ArrayList<>();

        for (int i = 1; i <= 1000; i++) {

            User user = new User();

            user.setUsername(String.format("usr%08d", i));

            user.setNombre(
                randomElement(NAMES) + " " + randomElement(LASTNAMES)
            );

            user.setPassword(
                "Clave" + String.format("%08d", i)
            );

            user.setCanCreate(random.nextDouble() > 0.5);
            user.setCanDelete(random.nextDouble() > 0.7);
            user.setCanEdit(random.nextDouble() > 0.4);

            user.setDireccion(
                "Calle " + (1 + random.nextInt(150))
                + " # " + (1 + random.nextInt(100))
                + "-" + (1 + random.nextInt(99))
            );

            user.setEmail("usuario" + i + "@example.com");

            user.setTelefono(
                "3" + String.format("%09d", random.nextInt(1_000_000_000))
            );

            users.add(user);
        }

        return users;
    }

    private List<Guide> generateGuides(List<User> users) {

        List<Guide> guides = new ArrayList<>();

        DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (int i = 1; i <= 9000; i++) {

            String topic = randomElement(TOPICS);
            String titlePrefix = randomElement(TITLE_PREFIXES);

            User author = users.get(random.nextInt(users.size()));

            Guide guide = new Guide();

            // Eight numeric digits, as required by Guide.
            guide.setGuideId(String.format("%08d", i));

            guide.setGuideAuthor(author.getUsername());

            guide.setGuideTopic(topic);

            guide.setGuideTitle(titlePrefix + topic);

            guide.setGuideBody(
                "Esta guia aborda el tema de " + topic
                + ". Su objetivo es explicar los conceptos "
                + "principales y proporcionar recomendaciones "
                + "para su implementacion. "
                + "Es importante comprender los fundamentos, "
                + "realizar pruebas y documentar cada procedimiento. "
                + "Se recomienda practicar los ejemplos y consultar "
                + "la documentacion oficial para profundizar "
                + "en los conocimientos adquiridos. "
                + "Guia numero " + i + "."
            );

            LocalDate publishDate = LocalDate.now()
                .minusDays(random.nextInt(1826));

            guide.setPublishDate(publishDate.format(formatter));

            guides.add(guide);
        }

        return guides;
    }

    private String randomElement(String[] elements) {
        return elements[random.nextInt(elements.length)];
    }
}
