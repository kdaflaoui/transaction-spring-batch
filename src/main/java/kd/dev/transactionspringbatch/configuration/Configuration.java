package kd.dev.transactionspringbatch.configuration;

import kd.dev.transactionspringbatch.entities.Transaction;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

@org.springframework.context.annotation.Configuration
@EnableBatchProcessing
public class Configuration {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private ItemReader<Transaction> transactionReader;
    @Autowired
    private ItemWriter<Transaction> transactionWriter;
    @Autowired
    private ItemProcessor<Transaction, Transaction> transationProcessor;

    @Bean
    public Job jobTransaction(){
        Step step = stepBuilderFactory.get("step-load-data")
                .<Transaction, Transaction>chunk(100)
                .reader(transactionReader)
                .processor(transationProcessor)
                .writer(transactionWriter)
                .build();

        return jobBuilderFactory.get("transaction-loader-job")
                .start(step)
                .build();
    }

    @Bean
    public FlatFileItemReader<Transaction> flateFileItemReader(@Value("${input.file}") Resource inputFile){
        FlatFileItemReader<Transaction> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setName("ffir_1");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setResource(inputFile);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;

    }

    @Bean
    public LineMapper<Transaction> lineMapper() {
        //créer un objet de type linemapper
        LineMapper<Transaction> lineMapper = new DefaultLineMapper<>();

        //créer un delimiter
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setNames("id", "accountID", "strTransactionDate", "transactionType", "amount");

        //set linetovkenizer dans linemapper
        ((DefaultLineMapper<Transaction>) lineMapper).setLineTokenizer(lineTokenizer);

        //créer l'objet Transaction
        BeanWrapperFieldSetMapper<Transaction> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Transaction.class);

        ((DefaultLineMapper<Transaction>) lineMapper).setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }



}
