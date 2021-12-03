package com.estudo.designpattern.config;

import com.estudo.designpattern.comic.Comic;
import com.estudo.designpattern.comic.ComicRepository;
import com.estudo.designpattern.creator.Creator;
import com.estudo.designpattern.creator.CreatorRepository;
import com.estudo.designpattern.user.User;
import com.estudo.designpattern.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreatorRepository creatorRepository;

    @Autowired
    private ComicRepository comicRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        User u1 = new User(null, "Henrique Zupper", "henriquezuper@zup.com.br", "417.701.438-90",
                sdf.parse("1995-08-11"));
        User u2 = new User(null, "Usuário Zupper", "usuariozuper@zup.com.br", "208.941.580-07",
                sdf.parse("1999-02-20"));

        User u3 = new User(null, "Marvel Zupper", "marvelzuper@zup.com.br", "556.448.560-20",
                sdf.parse("1999-02-20"));

        userRepository.saveAll(Arrays.asList(u1, u2, u3));

        Creator c1 = new Creator(1L, "Geoff Johns", "Writer");
        Creator c2 = new Creator(2L, "Scott Snyder", "Writer");
        Creator c3 = new Creator(3L, "Peter J", "Writer");
        Creator c4 = new Creator(4L, "Greg Rucka", "Writer");

        Creator c5 = new Creator(5L, "Gary Frank", "Artist");
        Creator c6 = new Creator(6L, "Ethan Van Sciver", "Artist");
        Creator c7 = new Creator(7L, "Phil Jimenez", "Artist");
        Creator c8 = new Creator(8L, "Mikel Janín", "Artist");

        Comic co1 = new Comic(1L, "DC Rebirth Omnibus Expanded Edition", 10.00, "1401276458",
                "The very foundations of the DC Universe are rocked here with this new edition of the DC REBIRTH OMNIBUS EXPANDED EDITION, collecting each best-selling REBIRTH special from 28 different series in one complete edition. Featuring contributions from some of the industry’s best writers, including Geoff Johns, Scott Snyder, Tom King, Peter J. Tomasi and Greg Rucka, and its most talented artists, such as Gary Frank, Ethan Van Sciver, Ivan Reis, Phil Jimenez, Mikel Janín, Doug Mahnke and Yanick Paquette, this expanded version features new Rebirth #1 issues, including JUSTICE LEAGUE OF AMERICA, BATWOMAN and more!");
        Comic co2 = new Comic(2L, "Flashpoint: The 10th Anniversary Omnibus", 20.00, "1401276450", "Barry Allen awoke in a world he barely recognizes, but it isn't a trick or a parallel Earth. Something or someone has altered time, replacing Barry's world with a new one. And this new world is not a safe place.");
        Comic co3 = new Comic(3L, "Batman by Grant Morrison Omnibus Vol. 3", 30.00, "1401276452", "Morrison continues his earth-shattering run on the Batman titles with this exciting series illustrated by hot artist Yanick Paquette who features the next stage of evolution of the Dark Knight. Bruce Wayne publicly announces that he is the financial backer of Batman and establishes a worldwide franchise of Batman that will protect the entire globe.");
        Comic co4 = new Comic(4L, "Superman by Grant Morrison Omnibus", 40.00, "1401276454", "Collecting the entirety of Morrison's epic saga, this New 52 era Superman omnibus celebrates and explores new facets of the Superman mythos. The compelling and deliberate Superman stories in this collection showcase his quintessential character and determination in a world that doesn't know what to make of him.");
        Comic co5 = new Comic(5L, "The Flash by Geoff Johns Omnibus Vol. 1", 50.00, "1401276456", "Nearly 20 years ago, writer Geoff Johns (Batman: Earth One, Shazam) took over The Flash and redefined a comic book icon for a new generation. During his unforgettable five-year run on the series, Johns would enrich the world of Keystone City and humanize both Wally West and his enemies like no one before.");

        creatorRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8));
        comicRepository.saveAll(Arrays.asList(co1, co2, co3, co4, co5));

        co1.getCreators().add(c1);
        co1.getCreators().add(c5);
        co2.getCreators().add(c2);
        co2.getCreators().add(c6);
        co3.getCreators().add(c3);
        co3.getCreators().add(c7);
        co4.getCreators().add(c4);
        co4.getCreators().add(c8);
        co5.getCreators().add(c2);
        co5.getCreators().add(c8);

        comicRepository.saveAll(Arrays.asList(co1, co2, co3, co4, co5));

        u1.getComics().add(co1);
        u1.getComics().add(co2);
        u1.getComics().add(co3);
        u2.getComics().add(co1);
        u2.getComics().add(co2);
        u2.getComics().add(co3);
        u2.getComics().add(co4);
        u2.getComics().add(co5);

        userRepository.saveAll(Arrays.asList(u1, u2));
    }

}
