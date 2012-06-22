package scalanlp.parser.models

import scalanlp.epic.Feature
import scalanlp.trees.{TreeInstance, BinarizedTree}
import scalanlp.parser._

case class DiscParserInference[L, W](featurizer: RefinedFeaturizer[L, W, Feature],
                                     ann: (BinarizedTree[L], Seq[W]) => BinarizedTree[(L, Int)],
                                     grammar: RefinedGrammar[L, W],
                                     baseMeasure: CoreGrammar[L, W]) extends ParserInference[L, W] {

  // E[T-z|T, params]
  def goldCounts(ti: TreeInstance[L, W], aug: CoreAnchoring[L, W]) = {
    val tree = ti.tree
    val words = ti.words
    val annotated = ann(tree, words)


    TreeMarginal(AugmentedGrammar.fromRefined(grammar), words, annotated).expectedCounts(featurizer)
  }

}
