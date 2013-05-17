package epic.features

/**
 *
 * @author dlwh
 */
trait WordFeaturizer[W] {
  def anchor(words: IndexedSeq[W]):WordFeatureAnchoring[W]
}