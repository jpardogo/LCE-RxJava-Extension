# Android Library providing and RxJava extension to follow Loading, Content &amp; Error pattern

Loading, Content &amp; Error pattern to create a consistent way of communication between ViewModel or Presenters and the View(Activities/Fragment/CustomViews).

Convert to observables the upstream type return from domain to provide:

- Boolean onLoading call twice on the begging of the process (true) and the end (false) 
- Content of the type defined
- Error in case the process is not successful. `LceErrorViewEntity` is provided as an optional wrapper for the throwable and extra info (Any model and message)

This pattern also detach the domain type return from the view layer, so in case of domain refactor or return type changes on domain, view layer is not affected.
