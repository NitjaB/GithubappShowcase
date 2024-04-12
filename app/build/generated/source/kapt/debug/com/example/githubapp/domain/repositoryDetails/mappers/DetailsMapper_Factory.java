package com.example.githubapp.domain.repositoryDetails.mappers;

import com.example.githubapp.domain.repository.mappers.DetailsMapper;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class DetailsMapper_Factory implements Factory<DetailsMapper> {
  @Override
  public DetailsMapper get() {
    return newInstance();
  }

  public static DetailsMapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static DetailsMapper newInstance() {
    return new DetailsMapper();
  }

  private static final class InstanceHolder {
    private static final DetailsMapper_Factory INSTANCE = new DetailsMapper_Factory();
  }
}
