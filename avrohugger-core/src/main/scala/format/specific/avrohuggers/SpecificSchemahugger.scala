package avrohugger
package format
package specific
package avrohuggers

import format.abstractions.avrohuggers.Schemahugger
import trees.{ SpecificCaseClassTree, SpecificObjectTree }
import matchers.TypeMatcher
import stores.{ClassStore, SchemaStore}

import org.apache.avro.Schema

import treehugger.forest.Tree

object SpecificSchemahugger extends Schemahugger {

  def toTrees(
    schemaStore: SchemaStore,
    classStore: ClassStore,
    namespace: Option[String],
    schema: Schema,
    typeMatcher: TypeMatcher,
    maybeBaseTrait: Option[String],
    maybeFlags: Option[List[Long]],
    restrictedFields: Boolean,
    targetScalaPartialVersion: String): List[Tree] = {

    val caseClassDef = SpecificCaseClassTree.toCaseClassDef(
      classStore,
      namespace,
      schema,
      typeMatcher,
      maybeBaseTrait,
      maybeFlags,
      restrictedFields,
      targetScalaPartialVersion)

    val companionDef = SpecificObjectTree.toCaseCompanionDef(
      schema,
      maybeFlags,
      schemaStore,
      typeMatcher)

    List(caseClassDef, companionDef)
  }

}
