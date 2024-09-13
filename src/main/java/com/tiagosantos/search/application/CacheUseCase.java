package com.tiagosantos.search.application;

import com.tiagosantos.search.domain.Cast;
import com.tiagosantos.search.domain.Messages;
import com.tiagosantos.search.domain.Movie;
import com.tiagosantos.search.infrastructure.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CacheUseCase {

    private final Document document;
    private final Cache cache;
    private final Data data;
    private final Log log;
    private final List<Movie> movies = new ArrayList<>();

    private int progress=0;
    private int error=0;
    private int warn=0;
    private final List<String> fileError= new ArrayList<>();
    private final List<String> fileWarning= new ArrayList<>();

    public CacheUseCase() {
        this.log = new StdOut();
        this.document = new Document("./data");
        this.data = new Data(this.log);
        this.cache = new Cache(this.log, "cache.data");
        this.cache.create();
    }

    public void execute() {
        try {
            java.io.File[] files = this.document.getListByFolder();

            if (files == null)
                throw new NullPointerException(String.format("%s %s", Messages.FILE_IS_NULL.getValue(), Messages.FILE_TYPE_SUPPORTED.getValue()));

            this.cache.clear();
            int total = files.length;
            for (File file : files) {
                try {
                    if (!file.isFile())
                        throw new IOException(String.format("%s \n %s", Messages.NOT_FILE.getValue(), file.getPath()));

                    String content = new String(java.nio.file.Files.readAllBytes(Paths.get(file.getPath())));
                    if (content.isEmpty())
                        throw new NullPointerException(String.format("%s %s", Messages.FILE_IS_NULL.getValue(), file.getPath()));

                    Regex texts = new Regex("(\\s\\d{4}(?!.*\\d{4}\\s))", content);
                    Regex yearString = new Regex("(\\d{4}(?!.*\\d{4}))", content);

                    if(texts.matchString().isEmpty())
                        throw new IOException(Messages.INVALID_CACHE_FILE.getValue());

                    int year = Integer.parseInt(yearString.matchStringGetFrist());

                    List<Cast> authorsList = new ArrayList<>();
                    if(texts.explodeString().length==2)
                    {
                    Regex authors = new Regex("\\b(?:\\w+\\s\\w+|\\w+\\s\\w+\\s\\w+|\\w+\\s\\w+\\s\\w+\\s\\w+|\\w+)\\b", texts.explodeString()[1]);
                    for (String author : authors.matchString()) {
                        String[] name = author.split(" ");
                        if (name.length == 1) {
                            String[] newName = Arrays.copyOf(name, name.length + 1);
                            newName[name.length] = "";
                            name = newName;
                        }
                        authorsList.add(new Cast(name[0], name[1]));
                    }
                    }else
                    {
                        this.log.Warn(Messages.NO_AUTHORS_FOUND.getValue());
                    }
                    this.movies.add(new Movie(texts.explodeString()[0], year, authorsList, file.getPath()));


                } catch (IOException errorIO) {
                    this.error++;
                    this.fileError.add(file.getPath());
                    this.log.Error(errorIO.getMessage());
                } catch (NullPointerException errorNullPoint) {
                    this.warn++;
                    this.fileWarning.add(file.getPath());
                    this.log.Warn(errorNullPoint.getMessage());
                } catch (Exception error) {
                    throw new Exception(String.format(Messages.ERROR_CREATING_CACHE.getValue(),error.getMessage(),file.getPath()));
                }

                progress++;
                this.log.Info(String.format(Messages.PROGRESS_STATUS.getValue(),progress, total,error,warn));
            }
            for (Movie movie : this.movies) {
                this.data.add(movie.getName(), movie.getPathFile());
                this.data.add(movie.getYear(), movie.getPathFile());

                for (Cast artist : movie.getCasts()) {
                    this.data.add(artist.getFistName(), movie.getPathFile());
                    this.data.add(artist.getLastName(), movie.getPathFile());
                    this.data.add(artist.getFullName(), movie.getPathFile());
                }
            }

            cache.write(this.data.getValues(), true);
            this.log.Info(Messages.CACHE_CREATED_SUCCESS.getValue());
            this.log.Info(String.format(Messages.ERROR_AND_WARNING_STATUS.getValue(),error,warn));
            this.log.Warn(String.format("%s",this.fileWarning));
            this.log.Error(String.format("%s",this.fileError));


        } catch (Exception error) {
            this.log.Error(error.getMessage());
        }
    }
}
