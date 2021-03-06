package strawman
package collection.immutable

/** Base trait for immutable set collections */
trait Set[A]
  extends collection.Set[A]
    with Iterable[A]
    with SetLike[A, Set]

/** Base trait for immutable set operations */
trait SetLike[A, +C[X] <: Set[X]]
  extends collection.SetLike[A, C]
    with SetMonoTransforms[A, C[A]]

/** Transformation operations returning a Set containing the same kind of
  * elements
  */
trait SetMonoTransforms[A, +Repr]
  extends collection.SetMonoTransforms[A, Repr] {

  def + (elem: A): Repr

  def - (elem: A): Repr

}