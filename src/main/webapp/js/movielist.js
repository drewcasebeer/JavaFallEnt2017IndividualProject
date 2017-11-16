
//Handle Movie Control Voting
$(document).on('click', '.movie-controls-bottom i', function() {
    var $movie = $(this).closest('.movie-wrapper');

    var movieId = $movie.attr('data-movieid');
    var movieName = $movie.find('.movie-controls-top').text();
    var year = $movie.attr('data-year').substring(0, 4);
    var value = $movie.find('.movie-score').attr('data-value');

    if($(this).hasClass('fa-thumbs-o-down')) {
        value--;
    } else {
        value++;

    }

    var params = {
        movieId: movieId,
        movieName: movieName,
        score: value,
        year: year
    };

    console.log(params);

    //Update Score
    $.post("upsertmovie", $.param(params), function(response) {
        console.log(response);

        $movie.find('.movie-score').attr('data-value', response.record.votes);
        $movie.find('.movie-score').text($movie.find('.movie-score').attr('data-value'));
    });
});