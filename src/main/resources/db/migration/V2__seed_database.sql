INSERT INTO publishers (name, slug) VALUES
    ('Editorial Sudamericana', 'editorial-sudamericana'),
    ('Anagrama', 'anagrama'),
    ('Penguin Random House', 'penguin-random-house'),
    ('HarperCollins', 'harpercollins'),
    ('Alfaguara', 'alfaguara'),
    ('Planeta', 'planeta'),
    ('Roca Editorial', 'roca-editorial'),
    ('Santillana', 'santillana'),
    ('Bloomsbury', 'bloomsbury'),
    ('Vintage Books', 'vintage-books');

INSERT INTO books (isbn, base_price, discount_percentage, cover, publication_date, publisher_id) VALUES
     ('9780142424179', 15.99, 10.00, 'http://images.cesguiro.es/books/9780142424179.webp', '1943-04-06', 1), -- 1. El principito
     ('9780142410363', 14.99, 5.00, 'http://images.cesguiro.es/books/9780142410363.webp', '1988-10-01', 2),  -- 2. Matilda
     ('9780142418222', 13.99, 15.00, 'http://images.cesguiro.es/books/9780142418222.jpeg', '1964-01-17', 2), -- 3. Charlie y la fábrica de chocolate
     ('9780060256654', 16.99, 15.00, 'http://images.cesguiro.es/books/9780060256654.webp', '1963-04-09', 3), -- 4. Donde viven los monstruos
     ('9780618260300', 18.99, 0.00, 'http://images.cesguiro.es/books/9780618260300.webp', '1950-10-16', 3), -- 5. El león, la bruja y el armario
     ('9780439554930', 19.99, 15.00, 'http://images.cesguiro.es/books/9780439554930.webp', '1997-06-26', 4), -- 6. Harry Potter y la piedra filosofal
     ('9781451673319', 12.99, 5.00, 'http://images.cesguiro.es/books/9781451673319.jpg', '1936-09-01', 5), -- 7. El cuento de Ferdinando
     ('9780064400558', 11.99, 0.00, 'http://images.cesguiro.es/books/9780064400558.webp', '1865-11-26', 5), -- 8. Alicia en el país de las maravillas
     ('9780439023528', 19.99, 0.00, 'http://images.cesguiro.es/books/9780439023528.webp', '2008-09-14', 3), -- 9. Los juegos del hambre
     ('9781423103349', 17.99, 0.00, 'http://images.cesguiro.es/books/9781423103349.webp', '2005-06-28', 4), -- 10. Percy Jackson y el ladrón del rayo
     ('9780316015844', 15.99, 0.00, 'http://images.cesguiro.es/books/9780316015844.webp', '2005-10-05', 2), -- 11. Crepúsculo
     ('9780062024039', 18.99, 10.00, 'http://images.cesguiro.es/books/9780062024039.webp', '2011-05-03', 5), -- 12. Divergente
     ('9781416914280', 17.99, 15.00, 'http://images.cesguiro.es/books/9781416914280.webp', '2007-03-27', 1), -- 13. Cazadores de sombras: Ciudad de hueso
     ('9780385737951', 16.99, 15.00, 'http://images.cesguiro.es/books/9780385737951.webp', '2009-10-06', 3), -- 14. El corredor del laberinto
     ('9781250047819', 14.99, 5.00, 'http://images.cesguiro.es/books/9781250047819.webp', '2013-02-26', 4), -- 15. Eleanor & Park
     ('9780142424180', 15.99, 15.00, 'http://images.cesguiro.es/books/9780142424180.webp', '2012-01-10', 2), -- 16. Bajo la misma estrella
     ('9780618260400', 14.99, 0.00, 'http://images.cesguiro.es/books/9780618260400.jpg', '1937-09-21', 3), -- 17. El hobbit
     ('9780261103573', 19.99, 0.00, 'http://images.cesguiro.es/books/9780261103573.webp', '1954-07-29', 3), -- 18. La comunidad del anillo
     ('9780261102361', 19.99, 0.00, 'http://images.cesguiro.es/books/9780261102361.webp', '1954-11-11', 3), -- 19. Las dos torres
     ('9780316029186', 18.99, 10.00, 'http://images.cesguiro.es/books/9780316029186.jpg', '1993-12-01', 4), -- 20. El último deseo
     ('9780316073797', 18.99, 5.00, 'http://images.cesguiro.es/books/9780316073797.jpeg', '1992-11-01', 4), -- 21. La espada del destino
     ('9780060557912', 16.99, 0.00, 'http://images.cesguiro.es/books/9780060557912.jpg', '1990-05-01', 5), -- 22. Buenos presagios
     ('9780060853983', 17.99, 15.00, 'http://images.cesguiro.es/books/9780060853983.webp', '2001-06-19', 5), -- 23. American Gods
     ('9780060557812', 15.99, 5.00, 'http://images.cesguiro.es/books/9780060557812.webp', '1999-02-01', 5); -- 24. Stardust

-- Insert book_translations (ESPAÑOL)
INSERT INTO book_translations (book_id, locale, title, synopsis) VALUES
     (1, 'es', 'El principito', 'El principito es una novela corta y la obra más famosa del escritor y aviador francés Antoine de Saint-Exupéry. La historia es filosófica y se trata de un joven príncipe que viaja de planeta en planeta, conociendo personajes que muestran distintas facetas de la naturaleza humana. En su viaje, el principito aprende sobre la soledad, el amor, la pérdida y la amistad.'),
     (2, 'es', 'Matilda', 'Matilda es una niña prodigio con unos padres terriblemente negligentes. A pesar de su ambiente familiar hostil, Matilda descubre su amor por la lectura y su capacidad telequinética. Cuando comienza la escuela, se encuentra con la terrible directora, la señorita Trunchbull, pero también con la amable señorita Honey. Con el tiempo, Matilda usa sus poderes para enfrentarse a las injusticias de los adultos en su vida.'),
     (3, 'es', 'Charlie y la fábrica de chocolate', 'Charlie Bucket es un niño pobre que vive con su familia cerca de la gigantesca fábrica de chocolate de Willy Wonka. Un día, Willy Wonka decide abrir las puertas de su fábrica a cinco niños afortunados que encuentren un billete dorado dentro de las chocolatinas Wonka. Charlie, contra todo pronóstico, encuentra uno de los billetes y su vida cambia para siempre al descubrir los secretos y maravillas de la fábrica de chocolate.'),
     (4, 'es', 'Donde viven los monstruos', 'Donde viven los monstruos es un cuento infantil escrito e ilustrado por Maurice Sendak. El libro describe la historia de Max, un niño travieso que se embarca en una aventura a un mundo de monstruos salvajes después de ser enviado a la cama sin cenar. En este mundo imaginario, Max se convierte en el rey de los monstruos pero pronto se da cuenta de que extraña a su familia y decide regresar a casa.'),
     (5, 'es', 'El león, la bruja y el armario', 'El león, la bruja y el ropero es una novela de fantasía infantil escrita por C.S. Lewis. Es el primer libro publicado y el segundo en la cronología interna de la serie Las crónicas de Narnia. La historia sigue a cuatro niños que descubren el mundo mágico de Narnia a través de un ropero y se unen al león Aslan para derrotar a la malvada Bruja Blanca.'),
     (6, 'es', 'Harry Potter y la piedra filosofal', 'Harry Potter es un niño huérfano que vive con sus abusivos tíos. En su undécimo cumpleaños, descubre que es un mago y es invitado a asistir al Colegio Hogwarts de Magia y Hechicería. Allí, Harry hace amigos y enemigos y descubre la verdad sobre sus padres y su conexión con el mago oscuro Voldemort.'),
     (7, 'es', 'El cuento de Ferdinando', 'El cuento de Ferdinando es una historia infantil escrita por Munro Leaf e ilustrada por Robert Lawson. El libro cuenta la historia de un toro pacífico llamado Ferdinando que prefiere oler flores en lugar de luchar. Cuando es picado por una abeja, su comportamiento cambia y es llevado a una corrida de toros, pero finalmente regresa a su vida tranquila en el campo.'),
     (8, 'es', 'Alicia en el país de las maravillas', 'Alicia en el país de las maravillas es una novela escrita por Lewis Carroll. La historia sigue a una joven llamada Alicia que cae por una madriguera de conejo y entra en un mundo fantástico lleno de criaturas peculiares y situaciones absurdas. A lo largo de su aventura, Alicia debe navegar por este mundo extraño y surrealista mientras trata de encontrar su camino de regreso a casa.'),
     (9, 'es', 'Los juegos del hambre', 'En un futuro distópico, la joven Katniss Everdeen debe participar en Los Juegos del Hambre, un evento televisado en el que 24 adolescentes luchan a muerte hasta que solo uno quede con vida. Katniss se ofrece como voluntaria para salvar a su hermana menor y deberá usar todas sus habilidades para sobrevivir.'),
     (10, 'es', 'Percy Jackson y el ladrón del rayo', 'Percy Jackson descubre que es un semidiós, hijo de Poseidón, y se embarca en una aventura para evitar una guerra entre los dioses del Olimpo. Junto con sus amigos, Percy debe recuperar el rayo robado de Zeus antes de que sea demasiado tarde.'),
     (11, 'es', 'Crepúsculo', 'Bella Swan se muda a la lluviosa ciudad de Forks y se enamora de Edward Cullen, un misterioso joven que resulta ser un vampiro. A medida que su relación se profundiza, Bella se ve envuelta en el peligroso mundo de los vampiros y debe enfrentarse a las amenazas que se ciernen sobre ellos.'),
     (12, 'es', 'Divergente', 'En una sociedad dividida en facciones basadas en virtudes humanas, Beatrice Prior descubre que es una Divergente, alguien que no encaja en ninguna facción. Cuando descubre una conspiración para destruir a los Divergentes, Beatrice debe encontrar su lugar y proteger a aquellos que ama.'),
     (13, 'es', 'Cazadores de sombras: Ciudad de hueso', 'Clary Fray descubre que pertenece a una raza de guerreros mitad ángeles llamados cazadores de sombras, cuya misión es proteger el mundo de los demonios. Junto a otros cazadores de sombras, Clary debe desentrañar el misterio de su pasado y luchar contra las fuerzas oscuras que amenazan su mundo.'),
     (14, 'es', 'El corredor del laberinto', 'Thomas despierta en un ascensor oscuro y ascendente, sin recordar nada excepto su nombre. Cuando las puertas se abren, se encuentra rodeado de otros chicos en un laberinto gigantesco y mortal. Thomas debe unir fuerzas con los demás para escapar y descubrir la verdad detrás del laberinto.'),
     (15, 'es', 'Eleanor & Park', 'Eleanor y Park son dos adolescentes inadaptados que se encuentran en el autobús escolar y forman una conexión inesperada a través de su amor por la música y los cómics. A medida que su relación crece, deben enfrentarse a los desafíos y prejuicios de la vida en su pequeño pueblo.'),
     (16, 'es', 'Bajo la misma estrella', 'Hazel Grace Lancaster es una adolescente con cáncer terminal que se enamora de Augustus Waters, un joven que asiste a su grupo de apoyo para pacientes con cáncer. Juntos, emprenden un viaje de amor y valentía mientras enfrentan la realidad de su enfermedad.'),
     (17, 'es', 'El hobbit', 'El hobbit cuenta la historia de Bilbo Bolsón, un hobbit que se embarca en una aventura inesperada para ayudar a un grupo de enanos a recuperar su montaña y su tesoro del dragón Smaug.'),
     (18, 'es', 'El señor de los anillos: La comunidad del anillo', 'La comunidad del anillo sigue a Frodo Bolsón y sus compañeros en su misión de destruir el Anillo Único, una poderosa reliquia creada por el señor oscuro Sauron.'),
     (19, 'es', 'El señor de los anillos: Las dos torres', 'Las dos torres continúa la misión de Frodo y Sam para destruir el Anillo Único, mientras Aragorn, Legolas y Gimli buscan a Merry y Pippin, que han sido capturados por orcos.'),
     (20, 'es', 'El último deseo', 'El último deseo es una colección de relatos que presenta al brujo Geralt de Rivia, un cazador de monstruos en un mundo de fantasía lleno de criaturas peligrosas y magia.'),
     (21, 'es', 'La espada del destino', 'La espada del destino es una colección de relatos que sigue las aventuras de Geralt de Rivia mientras enfrenta a monstruos y se cruza con personajes importantes en su vida.'),
     (22, 'es', 'Buenos presagios', 'Buenos presagios cuenta la historia de un ángel y un demonio que unen fuerzas para evitar el apocalipsis. Ambos han vivido en la Tierra durante siglos y se han encariñado con la humanidad, por lo que harán todo lo posible para detener el fin del mundo.'),
     (23, 'es', 'American Gods', 'American Gods sigue a Shadow Moon, un exconvicto que se convierte en guardaespaldas del enigmático Sr. Wednesday. Pronto descubre que su jefe es una encarnación de Odín, y se ve envuelto en una batalla entre los antiguos dioses y los nuevos.'),
     (24, 'es', 'Stardust', 'Stardust narra la aventura de Tristran Thorn, quien promete traerle a su amada una estrella caída. Su búsqueda lo lleva al reino mágico de Faerie, donde descubre que la estrella es una mujer y se enfrenta a peligros inesperados.');

-- Insert book_translations (INGLÉS)
INSERT INTO book_translations (book_id, locale, title, synopsis) VALUES
     (1, 'en', 'The Little Prince', 'The Little Prince is a novella and the most famous work of the French writer and aviator Antoine de Saint-Exupéry. The story is philosophical and involves a young prince who travels from planet to planet, meeting characters who show different aspects of human nature. In his journey, the little prince learns about loneliness, love, loss, and friendship.'),
     (2, 'en', 'Matilda', 'Matilda is a prodigy child with terribly negligent parents. Despite her hostile family environment, Matilda discovers her love of reading and her telekinetic ability. When she starts school, she encounters the terrible headmistress Miss Trunchbull, but also the kind Miss Honey. Over time, Matilda uses her powers to fight against the injustices of the adults in her life.'),
     (3, 'en', 'Charlie and the Chocolate Factory', 'Charlie Bucket is a poor boy living with his family near Willy Wonka''s gigantic chocolate factory. One day, Willy Wonka decides to open the doors of his factory to five lucky children who find a golden ticket inside Wonka chocolate bars. Charlie, against all odds, finds one of the tickets, and his life changes forever as he discovers the secrets and wonders of the chocolate factory.'),
     (4, 'en', 'Where the Wild Things Are', 'Where the Wild Things Are is a children''s book written and illustrated by Maurice Sendak. The book tells the story of Max, a mischievous boy who embarks on an adventure to a world of wild monsters after being sent to bed without dinner. In this imaginary world, Max becomes the king of the monsters but soon realizes he misses his family and decides to return home.'),
     (5, 'en', 'The Lion, the Witch and the Wardrobe', 'The Lion, the Witch and the Wardrobe is a children''s fantasy novel by C.S. Lewis. It is the first book published and the second in the chronological order of the series The Chronicles of Narnia. The story follows four children who discover the magical world of Narnia through a wardrobe and join the lion Aslan to defeat the evil White Witch.'),
     (6, 'en', 'Harry Potter and the Sorcerer''s Stone', 'Harry Potter is an orphaned boy living with his abusive aunt and uncle. On his eleventh birthday, he discovers he is a wizard and is invited to attend the Hogwarts School of Witchcraft and Wizardry. There, Harry makes friends and enemies and learns the truth about his parents and his connection to the dark wizard Voldemort.'),
     (7, 'en', 'The Story of Ferdinand', 'The Story of Ferdinand is a children''s book written by Munro Leaf and illustrated by Robert Lawson. The book tells the story of a peaceful bull named Ferdinand who prefers to smell flowers rather than fight. When he is stung by a bee, his behavior changes and he is taken to a bullfight, but he eventually returns to his quiet life in the countryside.'),
     (8, 'en', 'Alice''s Adventures in Wonderland', 'Alice''s Adventures in Wonderland is a novel written by Lewis Carroll. The story follows a young girl named Alice who falls down a rabbit hole and enters a fantastical world filled with peculiar creatures and absurd situations. Throughout her adventure, Alice must navigate this strange and surreal world as she tries to find her way back home.'),
     (9, 'en', 'The Hunger Games', 'In a dystopian future, young Katniss Everdeen must participate in The Hunger Games, a televised event where 24 teenagers fight to the death until only one remains. Katniss volunteers to save her younger sister and must use all her skills to survive.'),
     (10, 'en', 'Percy Jackson and the Olympians: The Lightning Thief', 'Percy Jackson discovers that he is a demigod, the son of Poseidon, and embarks on an adventure to prevent a war among the gods of Olympus. Along with his friends, Percy must retrieve Zeus'' stolen lightning bolt before it''s too late.'),
     (11, 'en', 'Twilight', 'Bella Swan moves to the rainy town of Forks and falls in love with Edward Cullen, a mysterious young man who turns out to be a vampire. As their relationship deepens, Bella becomes entangled in the dangerous world of vampires and must face the threats that loom over them.'),
     (12, 'en', 'Divergent', 'In a society divided into factions based on human virtues, Beatrice Prior discovers that she is Divergent, someone who does not fit into any faction. When she uncovers a conspiracy to destroy Divergents, Beatrice must find her place and protect those she loves.'),
     (13, 'en', 'The Mortal Instruments: City of Bones', 'Clary Fray discovers that she belongs to a race of half-angel warriors called Shadowhunters, whose mission is to protect the world from demons. Alongside other Shadowhunters, Clary must unravel the mystery of her past and fight against the dark forces that threaten her world.'),
     (14, 'en', 'The Maze Runner', 'Thomas wakes up in a dark, ascending elevator with no memory except his name. When the doors open, he finds himself surrounded by other boys in a massive, deadly maze. Thomas must join forces with the others to escape and uncover the truth behind the maze.'),
     (15, 'en', 'Eleanor & Park', 'Eleanor and Park are two misfit teenagers who meet on the school bus and form an unexpected connection through their love of music and comics. As their relationship grows, they must face the challenges and prejudices of life in their small town.'),
     (16, 'en', 'The Fault in Our Stars', 'Hazel Grace Lancaster is a teenager with terminal cancer who falls in love with Augustus Waters, a young man who attends her cancer patient support group. Together, they embark on a journey of love and bravery as they face the reality of their illness.'),
     (17, 'en', 'The Hobbit', 'The Hobbit tells the story of Bilbo Baggins, a hobbit who embarks on an unexpected adventure to help a group of dwarves reclaim their mountain and treasure from the dragon Smaug.'),
     (18, 'en', 'The Lord of the Rings: The Fellowship of the Ring', 'The Fellowship of the Ring follows Frodo Baggins and his companions on their mission to destroy the One Ring, a powerful relic created by the dark lord Sauron.'),
     (19, 'en', 'The Lord of the Rings: The Two Towers', 'The Two Towers continues Frodo and Sam''s mission to destroy the One Ring, while Aragorn, Legolas, and Gimli search for Merry and Pippin, who have been captured by orcs.'),
     (20, 'en', 'The Last Wish', 'The Last Wish is a collection of stories introducing the witcher Geralt of Rivia, a monster hunter in a fantasy world full of dangerous creatures and magic.'),
     (21, 'en', 'Sword of Destiny', 'Sword of Destiny is a collection of stories following Geralt of Rivia''s adventures as he faces monsters and encounters important characters in his life.'),
     (22, 'en', 'Good Omens', 'Good Omens tells the story of an angel and a demon who team up to prevent the apocalypse. Both have lived on Earth for centuries and have grown fond of humanity, so they will do everything possible to stop the end of the world.'),
     (23, 'en', 'American Gods', 'American Gods follows Shadow Moon, an ex-convict who becomes the bodyguard of the enigmatic Mr. Wednesday. He soon discovers that his boss is an incarnation of Odin, and he becomes embroiled in a battle between the old gods and the new.'),
     (24, 'en', 'Stardust', 'Stardust tells the adventure of Tristran Thorn, who promises to bring his beloved a fallen star. His quest leads him to the magical realm of Faerie, where he discovers that the star is a woman and faces unexpected dangers.');

INSERT INTO authors (name, slug) VALUES
     ('Antoine de Saint-Exupéry', 'antoine-de-saint-exupery'),
     ('Roald Dahl', 'roald-dahl'),
     ('Maurice Sendak', 'maurice-sendak'),
     ('C.S. Lewis', 'cs-lewis'),
     ('J.K. Rowling', 'jk-rowling'),
     ('Munro Leaf', 'munro-leaf'),
     ('Lewis Carroll', 'lewis-carroll'),
     ('Suzanne Collins', 'suzanne-collins'),
     ('Rick Riordan', 'rick-riordan'),
     ('Stephenie Meyer', 'stephenie-meyer'),
     ('Veronica Roth', 'veronica-roth'),
     ('Cassandra Clare', 'cassandra-clare'),
     ('James Dashner', 'james-dashner'),
     ('Rainbow Rowell', 'rainbow-rowell'),
     ('John Green', 'john-green'),
     ('Terry Pratchett', 'terry-pratchett'),
     ('Neil Gaiman', 'neil-gaiman'),
     ('Andrzej Sapkowski', 'andrzej-sapkowski'),
     ('J.R.R. Tolkien', 'jrr-tolkien');

-- Insert author_translations (ESPAÑOL)
INSERT INTO author_translations (author_id, locale, biography) VALUES
    (1, 'es', 'Antoine de Saint-Exupéry fue un escritor, poeta, aristócrata, periodista y aviador pionero francés (1900-1944). Es recordado por su novela corta El principito y por sus escritos líricos sobre la aviación.'),
    (2, 'es', 'Roald Dahl fue un novelista, cuentista, poeta, guionista y piloto de combate británico (1916-1990). Sus libros han vendido más de 250 millones de copias en todo el mundo.'),
    (3, 'es', 'Maurice Sendak fue un ilustrador y escritor estadounidense de libros infantiles (1928-2012). Se hizo conocido mundialmente por su libro Donde viven los monstruos.'),
    (4, 'es', 'C.S. Lewis fue un escritor y teólogo laico británico (1898-1963). Es conocido por sus obras de ficción, especialmente Las crónicas de Narnia.'),
    (5, 'es', 'J.K. Rowling es una autora británica, conocida por la serie Harry Potter (1965-Actualidad).'),
    (6, 'es', 'Munro Leaf fue un autor estadounidense de literatura infantil que escribió El cuento de Ferdinando (1905-1976).'),
    (7, 'es', 'Lewis Carroll fue un escritor, matemático, lógico, diácono anglicano y fotógrafo inglés (1832-1898). Es conocido por sus obras de fantasía Alicia en el país de las maravillas y su secuela A través del espejo.'),
    (8, 'es', 'Suzanne Collins es una guionista y autora estadounidense (1962-Actualidad), conocida por la serie Los Juegos del Hambre.'),
    (9, 'es', 'Rick Riordan es un autor estadounidense (1964-Actualidad), conocido por escribir la serie Percy Jackson y los dioses del Olimpo.'),
    (10, 'es', 'Stephenie Meyer es una novelista y productora de cine estadounidense (1973-Actualidad), conocida por su serie de romance vampírico Crepúsculo.'),
    (11, 'es', 'Veronica Roth es una novelista y escritora de cuentos estadounidense (1988-Actualidad), conocida por su trilogía debut Divergente, que fue un éxito de ventas en el New York Times.'),
    (12, 'es', 'Cassandra Clare es una autora estadounidense de ficción juvenil (1973-Actualidad), conocida por su serie Cazadores de sombras.'),
    (13, 'es', 'James Dashner es un autor estadounidense de ficción especulativa (1972-Actualidad), principalmente series para niños o jóvenes, como El corredor del laberinto.'),
    (14, 'es', 'Rainbow Rowell es una autora estadounidense (1973-Actualidad) conocida por sus novelas contemporáneas para jóvenes y adultos, incluyendo Eleanor & Park.'),
    (15, 'es', 'John Green es un autor estadounidense (1977-Actualidad) y creador de contenido en YouTube. Es el autor de Buscando a Alaska y Bajo la misma estrella.'),
    (16, 'es', 'Terry Pratchett fue un autor inglés de novelas de fantasía (1948-2015), conocido por su serie Mundodisco.'),
    (17, 'es', 'Neil Gaiman es un autor inglés de cuentos, novelas, cómics y novelas gráficas (1960-Actualidad). Sus obras notables incluyen la serie de cómics The Sandman y novelas Stardust, American Gods, y Good Omens.'),
    (18, 'es', 'Andrzej Sapkowski es un escritor de fantasía polaco (1948-Actualidad), conocido por su serie de libros The Witcher.'),
    (19, 'es', 'J.R.R. Tolkien fue un escritor, poeta, filólogo y académico inglés (1892-1973), conocido por El señor de los anillos.');

-- Insert author_translations (INGLÉS)
INSERT INTO author_translations (author_id, locale, biography) VALUES
    (1, 'en', 'Antoine de Saint-Exupéry was a French writer, poet, aristocrat, journalist, and pioneering aviator (1900-1944). He is best remembered for his novella The Little Prince and for his lyrical aviation writings.'),
    (2, 'en', 'Roald Dahl was a British novelist, short-story writer, poet, screenwriter, and wartime fighter pilot (1916-1990). His books have sold more than 250 million copies worldwide.'),
    (3, 'en', 'Maurice Sendak was an American illustrator and writer of children''s books (1928-2012). He became widely known for his book Where the Wild Things Are.'),
    (4, 'en', 'C.S. Lewis was a British writer and lay theologian (1898-1963). He is best known for his works of fiction, especially The Chronicles of Narnia.'),
    (5, 'en', 'J.K. Rowling is a British author, best known for the Harry Potter series (1965-Present).'),
    (6, 'en', 'Munro Leaf was an American author of children''s literature who wrote The Story of Ferdinand (1905-1976).'),
    (7, 'en', 'Lewis Carroll was an English writer, mathematician, logician, Anglican deacon, and photographer (1832-1898). He is best known for his fantasy works Alice''s Adventures in Wonderland and its sequel Through the Looking-Glass.'),
    (8, 'en', 'Suzanne Collins is an American television writer and author (1962-Present), best known for The Hunger Games series.'),
    (9, 'en', 'Rick Riordan is an American author (1964-Present), best known for writing the Percy Jackson & the Olympians series.'),
    (10, 'en', 'Stephenie Meyer is an American novelist and film producer (1973-Present), best known for her vampire romance series Twilight.'),
    (11, 'en', 'Veronica Roth is an American novelist and short story writer (1988-Present), known for her debut New York Times bestselling Divergent trilogy.'),
    (12, 'en', 'Cassandra Clare is an American author of young adult fiction (1973-Present), best known for her series The Mortal Instruments.'),
    (13, 'en', 'James Dashner is an American author of speculative fiction (1972-Present), primarily series for children or young adults, such as The Maze Runner.'),
    (14, 'en', 'Rainbow Rowell is an American author (1973-Present) known for young adult and adult contemporary novels, including Eleanor & Park.'),
    (15, 'en', 'John Green is an American author and YouTube content creator (1977-Present). He is the author of Looking for Alaska and The Fault in Our Stars.'),
    (16, 'en', 'Terry Pratchett was an English author of fantasy novels (1948-2015), best known for his Discworld series.'),
    (17, 'en', 'Neil Gaiman is an English author of short fiction, novels, comic books, graphic novels, audio theatre, and films (1960-Present). His notable works include the comic book series The Sandman and novels Stardust, American Gods, and Good Omens.'),
    (18, 'en', 'Andrzej Sapkowski is a Polish fantasy writer (1948-Present), best known for his book series The Witcher.'),
    (19, 'en', 'J.R.R. Tolkien was an English writer, poet, philologist, and academic (1892-1973), best known for The Lord of the Rings.');

INSERT INTO book_authors (book_id, author_id) VALUES
    (1, 1),  -- El principito by Antoine de Saint-Exupéry
    (2, 2),  -- Matilda by Roald Dahl
    (3, 2),  -- Charlie y la fábrica de chocolate by Roald Dahl
    (4, 3),  -- Donde viven los monstruos by Maurice Sendak
    (5, 4),  -- El león, la bruja y el ropero by C.S. Lewis
    (6, 5),  -- Harry Potter y la piedra filosofal by J.K. Rowling
    (7, 6),  -- El cuento de Ferdinando by Munro Leaf
    (8, 7),  -- Alicia en el país de las maravillas by Lewis Carroll
    (9, 8),  -- Los juegos del hambre by Suzanne Collins
    (10, 9), -- Percy Jackson y el ladrón del rayo by Rick Riordan
    (11, 10),-- Crepúsculo by Stephenie Meyer
    (12, 11),-- Divergente by Veronica Roth
    (13, 12),-- Cazadores de sombras: Ciudad de hueso by Cassandra Clare
    (14, 13),-- El corredor del laberinto by James Dashner
    (15, 14),-- Eleanor & Park by Rainbow Rowell
    (16, 15),-- Bajo la misma estrella by John Green
    (17, 19),-- El hobbit by J.R.R. Tolkien
    (18, 19),-- La comunidad del anillo by J.R.R. Tolkien
    (19, 19),-- Las dos torres by J.R.R. Tolkien
    (20, 18),-- El último deseo by Andrzej Sapkowski
    (21, 18),-- La espada del destino by Andrzej Sapkowski
    (22, 17),-- Buenos presagios by Neil Gaiman
    (22, 16),-- Buenos presagios by Terry Pratchett (Libro co-escrito)
    (23, 17),-- American Gods by Neil Gaiman
    (24, 17);-- Stardust by Neil Gaiman