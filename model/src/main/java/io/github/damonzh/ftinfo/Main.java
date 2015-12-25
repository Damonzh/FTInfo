package io.github.damonzh.ftinfo;

import io.github.damonzh.ftinfo.model.IMoviesModel;
import io.github.damonzh.ftinfo.model.impl.MoviesModelImpl;

/**
 * Author:      ZhangYan
 * Date:        15/12/24
 * Description:
 */
public class Main {

    public static void main(String[] args){
        IMoviesModel model = new MoviesModelImpl();
        model.getPopularMovies();
    }
}
