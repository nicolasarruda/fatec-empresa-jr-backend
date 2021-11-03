package com.empresajr.fatec.config;

import com.empresajr.fatec.entities.*;
import com.empresajr.fatec.entities.enums.TopicType;
import com.empresajr.fatec.repositories.AuthorRepository;
import com.empresajr.fatec.repositories.InternPostRepository;
import com.empresajr.fatec.repositories.PostRepository;
import com.empresajr.fatec.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


import java.time.Instant;
import java.util.Arrays;

import java.util.Date;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private InternPostRepository internPostRepository;

    @Override
    public void run(String... args) throws Exception {

        User user1 = new User(null, "marcos@mail.com");
        User user2 = new User(null, "ana@mail.com");

        Date date = Date.from(Instant.parse("2021-10-04T22:12:55Z"));

        Author author1 = new Author(null, "Maria", "maria@mail.com",
                "123456");

        Author author2 = new Author(null, "José", "jose@mail.com",
                "123456");

        authorRepository.saveAll(Arrays.asList(author1, author2));

        Topic topic1 = new Topic(null, "ADS");
        Topic topic2 = new Topic(null, "Sistemas Embarcados");
        Topic topic3 = new Topic(null, "Eventos");

        Post post1 = new Post(null,"Senhor dos aneis: A sociedade do Anel", date, topic1,
                "O Senhor dos Anéis (no original em inglês, The Lord of the Rings) é uma trilogia cinematográfica" +
                        " dirigida por Peter Jackson com base na obra-prima homónima de J. R. R. Tolkien." +
                        " Os três filmes foram rodados em simultâneo na Nova Zelândia, faturaram cerca de" +
                        " 3 bilhões (US$ 2.925.155.189) de dólares de receitas conjuntas de bilheteira"    +
                        " e foram galardoados com 17 Oscars, entre os 30 para os quais foram nomeados e"   +
                        " é a franquia cinematográfica mais premiada da história", "", author1);

        Post post2 = new Post(null,"Harry Potter e a Pedra Filosofal", date, topic2, "Harry Potter"      +
                " (Daniel Radcliffe) é um garoto órfão de dez anos que mora com seus desagradáveis tios, os Dursley,"       +
                " em Surrey. Na véspera de seu aniversário de onze anos, coisas incomuns começam a acontecer, como ir"      +
                " no zoológico com seu mimado primo Duda e descobrir que consegue falar com uma cobra e ainda fazer"        +
                " o vidro de proteção da serpente desaparecer sem explicação. Perto do seu 11º aniversário, cartas da"      +
                " Escola de Magia e Bruxaria de Hogwarts ficam sendo enviadas pra ele, que seus tios tentam a todo custo"   +
                " destruir, mas não conseguem. No dia de seu aniversário, Harry e seus tios são surpreendidos por um"       +
                " homem estranho, Rúbeo Hagrid (Robbie Coltrane), que lhe conta que Harry é um bruxo assim como seus"       +
                " pais também eram. Os tios de Harry tentaram esconder isso desde que o aceitaram quando ele ainda era"     +
                " um bebê. Após uma discussão, Hagrid finalmente entrega a carta, que diz que Harry foi convidado a"        +
                " ingressar na Escola de Magia e Feitiçaria de Hogwarts. Ele deixa Harry escolher se quer ir para "         +
                "Hogwarts ou ficar com seus tios. Harry opta por ir a Hogwarts. Depois de comprar material escolar junto"   +
                " com Hagrid em uma rua de bruxos escondida, o Beco Diagonal, Harry descobre que apesar de não saber "      +
                "nada sobre o Mundo Mágico, ele é famoso e conhecido por todos, e a razão disso é por que o maior Bruxo"    +
                " das Trevas, Lorde Voldemort, foi quem assassinou Lilian e Thiago Potter e na tentativa de matar Harry,"   +
                " algo aconteceu o feitiço de Voldemort ricocheteou nele mesmo o fazendo perder seus poderes e desaparecer" +
                ", deixando apenas uma cicatriz em forma de raio na testa de Harry. Após terminar de comprar o material,"   +
                " Harry embarca no Expresso de Hogwarts através da Plataforma 9 ¾ na Estação de King's Cross.", "", author2);

        InternPost internpost1 = new InternPost(null,"Senhor dos aneis: A sociedade do Anel", date,
                "O Senhor dos Anéis (no original em inglês, The Lord of the Rings) é uma trilogia cinematográfica" +
                        " dirigida por Peter Jackson com base na obra-prima homónima de J. R. R. Tolkien." +
                        " Os três filmes foram rodados em simultâneo na Nova Zelândia, faturaram cerca de" +
                        " 3 bilhões (US$ 2.925.155.189) de dólares de receitas conjuntas de bilheteira"    +
                        " e foram galardoados com 17 Oscars, entre os 30 para os quais foram nomeados e"   +
                        " é a franquia cinematográfica mais premiada da história", "");

        InternPost internpost2 = new InternPost(null,"Harry Potter e a Pedra Filosofal", date, "Harry Potter"      +
                " (Daniel Radcliffe) é um garoto órfão de dez anos que mora com seus desagradáveis tios, os Dursley,"       +
                " em Surrey. Na véspera de seu aniversário de onze anos, coisas incomuns começam a acontecer, como ir"      +
                " no zoológico com seu mimado primo Duda e descobrir que consegue falar com uma cobra e ainda fazer"        +
                " o vidro de proteção da serpente desaparecer sem explicação. Perto do seu 11º aniversário, cartas da"      +
                " Escola de Magia e Bruxaria de Hogwarts ficam sendo enviadas pra ele, que seus tios tentam a todo custo"   +
                " destruir, mas não conseguem. No dia de seu aniversário, Harry e seus tios são surpreendidos por um"       +
                " homem estranho, Rúbeo Hagrid (Robbie Coltrane), que lhe conta que Harry é um bruxo assim como seus"       +
                " pais também eram. Os tios de Harry tentaram esconder isso desde que o aceitaram quando ele ainda era"     +
                " um bebê. Após uma discussão, Hagrid finalmente entrega a carta, que diz que Harry foi convidado a"        +
                " ingressar na Escola de Magia e Feitiçaria de Hogwarts. Ele deixa Harry escolher se quer ir para "         +
                "Hogwarts ou ficar com seus tios. Harry opta por ir a Hogwarts. Depois de comprar material escolar junto"   +
                " com Hagrid em uma rua de bruxos escondida, o Beco Diagonal, Harry descobre que apesar de não saber "      +
                "nada sobre o Mundo Mágico, ele é famoso e conhecido por todos, e a razão disso é por que o maior Bruxo"    +
                " das Trevas, Lorde Voldemort, foi quem assassinou Lilian e Thiago Potter e na tentativa de matar Harry,"   +
                " algo aconteceu o feitiço de Voldemort ricocheteou nele mesmo o fazendo perder seus poderes e desaparecer" +
                ", deixando apenas uma cicatriz em forma de raio na testa de Harry. Após terminar de comprar o material,"   +
                " Harry embarca no Expresso de Hogwarts através da Plataforma 9 ¾ na Estação de King's Cross.", "");
        
        userRepository.saveAll(Arrays.asList(user1, user2));
        postRepository.saveAll(Arrays.asList(post1, post2));
        internPostRepository.saveAll(Arrays.asList(internpost1, internpost2));

    }
}
