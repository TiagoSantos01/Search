package com.tiagosantos.search.application;

import com.tiagosantos.search.infrastructure.*;

import java.util.*;


public class SeachUseCase {

    private final Cache cache;
    private final Data data;
    private final Log log;
    private final Time time;

    public SeachUseCase() {
        this.log = new StdOut();
        this.data = new Data(this.log);
        this.time = new Time(this.log);
        this.cache = new Cache(this.log, "cache.data");
    }


    public void execute(String search) {

        HashMap<String, List<String>> values = this.cache.read();
        this.data.setValues(values);
        this.time.start();
        List<String> resultSearch= this.data.find(search);

        if (resultSearch.isEmpty()) {
            List<String> wordRegex = new ArrayList<>();
            String[] words = search.split(" ");
            for (String word : words) {
                wordRegex.add(String.format("(?=.*\\b%s\\b)", word));
            }
            for (String key : this.data.getValues().keySet()) {
                Regex regex = new Regex(String.join("", wordRegex), key);
                if (!regex.matchString().isEmpty()) {
                    resultSearch.addAll(this.data.find(key));
                    break;
                }
            }
        }
        this.time.end();
        this.time.getResult();
        Set<String> removedDuplicateResul = new HashSet<>(resultSearch);
        List<String> resultSearchOrder= new ArrayList<>(removedDuplicateResul);
        Collections.sort(resultSearchOrder);
        System.out.println(String.format("Foram encontradas %s ocorrências pelo termo \"%s\"", resultSearchOrder.size(), search));
        System.out.println(String.format("Os arquivos que possuem \"%s\" são:", search));
        System.out.println(String.join("\n", resultSearchOrder));


    }
}
