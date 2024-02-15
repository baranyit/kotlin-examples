package hu.btnet.reactivity

import kotlinx.coroutines.*
import reactor.core.publisher.Mono

fun main() = runBlocking {
    SimpleDTO(1).toMonoIfIdNotNullEX().block().let { println(it) }
    println(toMonoIfIdNotNull(SimpleDTO(1)).block())
}

class SimpleDTO(val id: Int? = null)

fun toMonoIfIdNotNull(dto: SimpleDTO): Mono<Int> {
    return if (dto.id != null) {
        Mono.just(dto.id)
    } else {
        Mono.error(IllegalArgumentException("ID is null"))
    }
}

fun SimpleDTO.toMonoIfIdNotNullEX(): Mono<Int> =
    id?.let { Mono.just(it) } ?: Mono.error(IllegalArgumentException("ID is null"))
