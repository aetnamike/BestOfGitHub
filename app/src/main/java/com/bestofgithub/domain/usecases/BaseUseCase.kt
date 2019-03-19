package com.bestofgithub.domain.usecases

abstract class BaseUseCase<in ParamsT, out ResultT> {
    abstract fun execute(params: ParamsT): ResultT
}