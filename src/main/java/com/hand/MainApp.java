package com.hand;

import com.hand.inter.IFilm;

import com.hand.pojo.Film;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.IOException;
import java.io.Reader;


public class MainApp {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("applicationContext-mybatis.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public static void main(String[] args) {
        Film film=new Film();
        film.setTitle(args[5]);
        film.setDescription(args[6]);
        film.setFilm_id(10);
        film.setSpecial_features("dffg");
        film.setReplacement_cost(22);
        film.setRental_rate(33);
        film.setRelease_year("1997");
        film.setRental_duration(6);
        film.setLanguage_id(1);
        film.setRating("PG");
        film.setLength(106);

        SqlSession session = sqlSessionFactory.openSession();
        try {
            IFilm userOperation=session.getMapper(IFilm.class);
            userOperation.addFilm(film);
            session.commit();

        } finally {
            session.close();
        }

    }
    }


