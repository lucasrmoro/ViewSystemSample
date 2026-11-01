package br.com.viewsystem_sample.structural.di

/** Identifies a named binding in addition to its type; equal values address the same binding. */
interface DependencyQualifier {

    val value: String

}
