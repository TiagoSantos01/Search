package com.tiagosantos.search.application;

import com.tiagosantos.search.domain.Artist;
import com.tiagosantos.search.domain.Messages;
import com.tiagosantos.search.domain.Movie;
import com.tiagosantos.search.infrastructure.Convert;
import com.tiagosantos.search.infrastructure.Document;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CacheUseCase {

    private final Document document;
    private Convert<Movie> convert;
    private List<Movie> movies= new ArrayList<>();
    private HashMap<String,List<String>> cache= new HashMap<>();
    public  CacheUseCase(){
         this.document= new Document("./data");
         this.convert= new Convert<>();
    }

    public void execute(){
    try {
        java.io.File[] files = this.document.getListByFolder();

        if (files == null)
            throw new NullPointerException(String.format("%s %s", Messages.FILE_IS_NULL.getValue(), Messages.FILE_TYPE_SUPPORTED.getValue()));

        for(java.io.File file : files)
        {
            try {
                if (!file.isFile())
                    throw  new IOException(String.format("Error: %s \n %s",Messages.NOT_FILE.getValue(),file.getPath()));

                String content = new String (java.nio.file.Files.readAllBytes(Paths.get(file.getPath())));

//(\d{4}(?!.*\d{4})) pega o ano
                String[] texts= content.split("(\\s\\d{4}(?!.*\\d{4}\\s))");

                Pattern pattern = Pattern.compile("(\\d{4}(?!.*\\d{4}))");

                Matcher matcher = pattern.matcher(content);

                if(!matcher.find())
                    throw new Exception("Não foi encontrado o ano no arquivo");
                int year= Integer.parseInt(matcher.group());


                Pattern patternArtist = Pattern.compile("\\b(?:\\w+\\s\\w+|\\w+\\s\\w+\\s\\w+|\\w+\\s\\w+\\s\\w+\\s\\w+|\\w+)\\b");
                Matcher matcherArtist = patternArtist.matcher(texts[1]);

//                if(!matcherArtist.find())
//                    throw new Exception("Não foi encontrado o atores no arquivo");

                List<Artist> authors= new ArrayList<>();
                while (matcherArtist.find()) {
                    String author = matcherArtist.group().trim();
                    if (!author.isEmpty()) {
                    String[] name= author.split(" ");
                    if(name.length==1) {
                        String[] newName = Arrays.copyOf(name, name.length + 1);
                        newName[name.length]="";
                        name=newName;
                    }

                    authors.add(new Artist(name[0],name[1]));
                    }
                }

                this.movies.add(new Movie(texts[0],year,authors,file.getPath()));



                for(Movie movie : this.movies)
                {
                    if(this.cache.containsKey(movie.getName()))
                        this.cache.get(movie.getName()).add(movie.getPathFile());
                        else {
                        List<String> movieList = new ArrayList<>();
                        movieList.add(movie.getPathFile());
                        this.cache.put(movie.getName(), movieList);
                    }

                    if(this.cache.containsKey(Integer.toString(movie.getYear())))
                        this.cache.get(Integer.toString(movie.getYear())).add(movie.getPathFile());
                    else {
                        List<String> movieList = new ArrayList<>();
                        movieList.add(movie.getPathFile());
                        this.cache.put(Integer.toString(movie.getYear()), movieList);
                    }

                    for(Artist artist: movie.getArtists()) {
                        if (this.cache.containsKey(artist.getFullName()))
                            this.cache.get(artist.getFullName()).add(movie.getPathFile());
                        else {
                            List<String> movieList = new ArrayList<>();
                            movieList.add(movie.getPathFile());
                            this.cache.put(artist.getFullName(), movieList);
                        }

                        if (this.cache.containsKey(artist.getFistName()))
                            this.cache.get(artist.getFistName()).add(movie.getPathFile());
                        else {
                            List<String> movieList = new ArrayList<>();
                            movieList.add(movie.getPathFile());
                            this.cache.put(artist.getFistName(), movieList);
                        }

                        if (this.cache.containsKey(artist.getLastName()))
                            this.cache.get(artist.getLastName()).add(movie.getPathFile());
                        else {
                            List<String> movieList = new ArrayList<>();
                            movieList.add(movie.getPathFile());
                            this.cache.put(artist.getLastName(), movieList);
                        }
                    }
                }



            }catch (IOException errorIO)
            {
                System.out.print(String.format("Error: %s", errorIO.getMessage()));

            }
        }
//                this.document.Save(this.cache);

                System.out.print(this.movies.toString());
    }catch (Exception error)
    {
        System.out.print(String.format("Error: %s", error.getMessage()));
    }

    }
}
