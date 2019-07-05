import com.jpardogo.example.domain.common.ThreadScheduler
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.schedulers.Schedulers

val mockThreadScheduler: ThreadScheduler = mock {
    on { it.io() } doReturn Schedulers.trampoline()
    on { it.main() } doReturn Schedulers.trampoline()
    on { it.computation() } doReturn Schedulers.trampoline()
}