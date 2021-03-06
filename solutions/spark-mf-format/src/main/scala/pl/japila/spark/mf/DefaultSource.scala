package pl.japila.spark.mf

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.FileStatus
import org.apache.hadoop.mapreduce.Job
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.catalyst.InternalRow
import org.apache.spark.sql.execution.datasources.{FileFormat, OutputWriterFactory, PartitionedFile}
import org.apache.spark.sql.sources.Filter
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.unsafe.types.UTF8String

class DefaultSource extends FileFormat {
  override def inferSchema(sparkSession: SparkSession, options: Map[String, String], files: Seq[FileStatus]): Option[StructType] = {
    println(s">>> inferSchema($files)")
    Some(StructType(
      StructField("line", StringType, nullable = true) :: Nil
    ))
  }

  override def prepareWrite(sparkSession: SparkSession, job: Job, options: Map[String, String], dataSchema: StructType): OutputWriterFactory = {
    println(">>> prepareWrite")
    null
  }

  override def buildReader(sparkSession: SparkSession, dataSchema: StructType, partitionSchema: StructType, requiredSchema: StructType, filters: Seq[Filter], options: Map[String, String], hadoopConf: Configuration): (PartitionedFile) => Iterator[InternalRow] = {
    pf => Iterator(InternalRow(UTF8String.fromString("hello")))
  }
}
