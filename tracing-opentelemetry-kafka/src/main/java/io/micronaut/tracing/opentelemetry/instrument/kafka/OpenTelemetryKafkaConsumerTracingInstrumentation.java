/*
 * Copyright 2017-2023 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.tracing.opentelemetry.instrument.kafka;

import io.micronaut.context.event.BeanCreatedEvent;
import io.micronaut.context.event.BeanCreatedEventListener;
import io.opentelemetry.instrumentation.kafkaclients.v2_6.KafkaTelemetry;

import org.apache.kafka.clients.consumer.Consumer;

import jakarta.inject.Singleton;

/**
 * Kafka consumer tracing instrumentation using OpenTelemetry.
 *
 * @since 4.5.0
 */
@Singleton
public class OpenTelemetryKafkaConsumerTracingInstrumentation implements BeanCreatedEventListener<Consumer<?, ?>> {

    private final KafkaTelemetry kafkaTelemetry;

    /**
     * Default constructor.
     *
     * @param kafkaTelemetry The kafka telemetry
     */
    public OpenTelemetryKafkaConsumerTracingInstrumentation(KafkaTelemetry kafkaTelemetry) {
        this.kafkaTelemetry = kafkaTelemetry;
    }

    @Override
    public Consumer<?, ?> onCreated(BeanCreatedEvent<Consumer<?, ?>> event) {
        return kafkaTelemetry.wrap(event.getBean());
    }
}
