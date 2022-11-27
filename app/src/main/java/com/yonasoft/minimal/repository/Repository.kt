package com.yonasoft.minimal.repository

import com.yonasoft.minimal.model.anime_detail_model.AnimeDetail
import com.yonasoft.minimal.model.anime_model.Anime
import com.yonasoft.minimal.network.MALAuth
import com.yonasoft.minimal.network.RetrofitInstance
import retrofit2.Response
import javax.inject.Singleton

@Singleton
class Repository(
) {
    suspend fun getAnimeRanking(
        rankingType: String,
        limit: Int,
        offset: Int,
        fields: String
    ): Response<Anime> {
        return RetrofitInstance.api.getAnimeRanking(
            clientId = MALAuth.clientId,
            rankingType = rankingType,
            limit = limit,
            offset = offset,
            fields = fields
        )
    }

    suspend fun getAnimeSeasonal(
        year: Int,
        season: String,
        sort: String,
        limit: Int,
        offset: Int,
        fields: String
    ): Response<Anime> {
        return RetrofitInstance.api.getAnimeSeasonal(
            clientId = MALAuth.clientId,
            year = year,
            season = season,
            sort = sort,
            limit = limit,
            offset = offset,
            fields = ""
        )
    }

    suspend fun getAnimeSuggested(
        limit: Int,
        offset: Int,
        fields: String = ""
    ): Response<Anime> {
        return RetrofitInstance.api.getAnimeSuggested(
            clientId = MALAuth.clientId,
            limit = limit,
            offset = offset,
            fields = fields
        )
    }

    suspend fun getAnimeDetails(
        clientId: String = MALAuth.clientId,
        animeId: Int,
    ):Response<AnimeDetail> {
        return RetrofitInstance.api.getAnimeDetails(
            clientId = clientId,
            animeId = animeId,
            fields = "id,title,main_picture,alternative_titles,start_date,end_date,synopsis,mean,rank,popularity,num_list_users,num_scoring_users,nsfw,created_at,updated_at,media_type,status,genres,my_list_status,num_episodes,start_season,broadcast,source,average_episode_duration,rating,pictures,background,related_anime,related_manga,recommendations,studios,statistics"
        )
    }
}