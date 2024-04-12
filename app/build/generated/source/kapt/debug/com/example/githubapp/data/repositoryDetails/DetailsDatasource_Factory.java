package com.example.githubapp.data.repositoryDetails;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class DetailsDatasource_Factory implements Factory<RepositoryDetailsDatasource> {
  private final Provider<RepositoryDetailsApi> detailsApiProvider;

  public DetailsDatasource_Factory(Provider<RepositoryDetailsApi> detailsApiProvider) {
    this.detailsApiProvider = detailsApiProvider;
  }

  @Override
  public RepositoryDetailsDatasource get() {
    return newInstance(detailsApiProvider.get());
  }

  public static DetailsDatasource_Factory create(Provider<RepositoryDetailsApi> detailsApiProvider) {
    return new DetailsDatasource_Factory(detailsApiProvider);
  }

  public static RepositoryDetailsDatasource newInstance(RepositoryDetailsApi detailsApi) {
    return new RepositoryDetailsDatasource(detailsApi);
  }
}
