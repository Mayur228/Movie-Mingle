package com.theappmakerbuddy.moviemingle.filmdetail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theappmakerbuddy.moviemingle.cast.domain.usecase.GetMovieCastUseCase
import com.theappmakerbuddy.moviemingle.cast.domain.usecase.GetTvCastUseCase
import com.theappmakerbuddy.moviemingle.filmdetail.data.repository.FilmsDetailsRepository
import com.theappmakerbuddy.moviemingle.common.util.Resource
import com.theappmakerbuddy.moviemingle.favorites.data.data.local.Favorite
import com.theappmakerbuddy.moviemingle.favorites.domain.repository.FavoritesRepository
import com.theappmakerbuddy.moviemingle.filmdetail.presentation.FilmDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmDetailsViewModel @Inject constructor(
    private val repository: FilmsDetailsRepository,
    private val favoritesRepository: FavoritesRepository,
    private val getTvCastUseCase: GetTvCastUseCase,
    private val getMovieCastUseCase: GetMovieCastUseCase,
) : ViewModel() {
    private val _filmDetailsUiState = MutableStateFlow(FilmDetailsUiState())
    val filmDetailsUiState = _filmDetailsUiState.asStateFlow()

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _filmDetailsUiState.update {
                it.copy(
                    isLoading = true
                )
            }
            when (val result = repository.getMoviesDetails(movieId)) {
                is Resource.Error -> {
                    _filmDetailsUiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }

                is Resource.Success -> {
                    _filmDetailsUiState.update {
                        it.copy(
                            isLoading = false,
                            movieDetails = result.data
                        )
                    }
                }

                else -> {
                    filmDetailsUiState
                }
            }
        }
    }

    fun getTvSeriesDetails(tvId: Int) {
        viewModelScope.launch {
            _filmDetailsUiState.update {
                it.copy(
                    isLoading = true
                )
            }
            when (val result = repository.getTvSeriesDetails(tvId)) {
                is Resource.Error -> {
                    _filmDetailsUiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }

                is Resource.Success -> {
                    _filmDetailsUiState.update {
                        it.copy(
                            isLoading = false,
                            tvSeriesDetails = result.data
                        )
                    }
                }

                else -> {
                    filmDetailsUiState
                }
            }
        }
    }

    fun getMovieCasts(movieId: Int) {
        viewModelScope.launch {
            _filmDetailsUiState.update {
                it.copy(isLoadingCasts = true)
            }
            when (val result = getMovieCastUseCase(movieId)) {
                is Resource.Error -> {
                    _filmDetailsUiState.update {
                        it.copy(
                            isLoadingCasts = false,
                            errorCasts = result.message
                        )
                    }
                }

                is Resource.Success -> {
                    _filmDetailsUiState.update {
                        it.copy(
                            isLoadingCasts = false,
                            credits = result.data
                        )
                    }
                }

                else -> {
                    filmDetailsUiState
                }
            }
        }
    }

    fun getTvSeriesCasts(tvId: Int) {
        viewModelScope.launch {
            _filmDetailsUiState.update {
                it.copy(isLoadingCasts = true)
            }
            when (val result = getTvCastUseCase(tvId)) {
                is Resource.Error -> {
                    _filmDetailsUiState.update {
                        it.copy(
                            isLoadingCasts = false,
                            errorCasts = result.message
                        )
                    }
                }

                is Resource.Success -> {
                    _filmDetailsUiState.update {
                        it.copy(
                            isLoadingCasts = false,
                            credits = result.data
                        )
                    }
                }

                else -> {
                    filmDetailsUiState
                }
            }
        }
    }

    fun getFilmDetails(filmId: Int, filmType: String) {
        if (filmType == "movie") {
            getMovieDetails(filmId)
            getMovieCasts(filmId)
        } else {
            getTvSeriesDetails(filmId)
            getTvSeriesCasts(filmId)
        }
    }

    fun isAFavorite(mediaId: Int): LiveData<Boolean> {
        return favoritesRepository.isFavorite(mediaId)
    }

    fun insertFavorite(favorite: Favorite) {
        viewModelScope.launch {
            favoritesRepository.insertFavorite(favorite)
        }
    }

    fun deleteFavorite(favorite: Favorite) {
        viewModelScope.launch {
            favoritesRepository.deleteOneFavorite(favorite)
        }
    }
}
