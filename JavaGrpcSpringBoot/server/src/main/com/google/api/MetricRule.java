// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/api/quota.proto

package com.google.api;

/**
 * <pre>
 * Bind API methods to metrics. Binding a method to a metric causes that
 * metric's configured quota behaviors to apply to the method call.
 * </pre>
 *
 * Protobuf type {@code google.api.MetricRule}
 */
public  final class MetricRule extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:google.api.MetricRule)
    MetricRuleOrBuilder {
private static final long serialVersionUID = 0L;
  // Use MetricRule.newBuilder() to construct.
  private MetricRule(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MetricRule() {
    selector_ = "";
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private MetricRule(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            String s = input.readStringRequireUtf8();

            selector_ = s;
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000002) != 0)) {
              metricCosts_ = com.google.protobuf.MapField.newMapField(
                  MetricCostsDefaultEntryHolder.defaultEntry);
              mutable_bitField0_ |= 0x00000002;
            }
            com.google.protobuf.MapEntry<String, Long>
            metricCosts__ = input.readMessage(
                MetricCostsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
            metricCosts_.getMutableMap().put(
                metricCosts__.getKey(), metricCosts__.getValue());
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.google.api.QuotaProto.internal_static_google_api_MetricRule_descriptor;
  }

  @SuppressWarnings({"rawtypes"})
  @Override
  protected com.google.protobuf.MapField internalGetMapField(
      int number) {
    switch (number) {
      case 2:
        return internalGetMetricCosts();
      default:
        throw new RuntimeException(
            "Invalid map field number: " + number);
    }
  }
  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.google.api.QuotaProto.internal_static_google_api_MetricRule_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            MetricRule.class, Builder.class);
  }

  private int bitField0_;
  public static final int SELECTOR_FIELD_NUMBER = 1;
  private volatile Object selector_;
  /**
   * <pre>
   * Selects the methods to which this rule applies.
   * Refer to [selector][google.api.DocumentationRule.selector] for syntax details.
   * </pre>
   *
   * <code>string selector = 1;</code>
   */
  public String getSelector() {
    Object ref = selector_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      selector_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * Selects the methods to which this rule applies.
   * Refer to [selector][google.api.DocumentationRule.selector] for syntax details.
   * </pre>
   *
   * <code>string selector = 1;</code>
   */
  public com.google.protobuf.ByteString
      getSelectorBytes() {
    Object ref = selector_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      selector_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int METRIC_COSTS_FIELD_NUMBER = 2;
  private static final class MetricCostsDefaultEntryHolder {
    static final com.google.protobuf.MapEntry<
        String, Long> defaultEntry =
            com.google.protobuf.MapEntry
            .<String, Long>newDefaultInstance(
                com.google.api.QuotaProto.internal_static_google_api_MetricRule_MetricCostsEntry_descriptor, 
                com.google.protobuf.WireFormat.FieldType.STRING,
                "",
                com.google.protobuf.WireFormat.FieldType.INT64,
                0L);
  }
  private com.google.protobuf.MapField<
      String, Long> metricCosts_;
  private com.google.protobuf.MapField<String, Long>
  internalGetMetricCosts() {
    if (metricCosts_ == null) {
      return com.google.protobuf.MapField.emptyMapField(
          MetricCostsDefaultEntryHolder.defaultEntry);
    }
    return metricCosts_;
  }

  public int getMetricCostsCount() {
    return internalGetMetricCosts().getMap().size();
  }
  /**
   * <pre>
   * Metrics to update when the selected methods are called, and the associated
   * cost applied to each metric.
   * The key of the map is the metric name, and the values are the amount
   * increased for the metric against which the quota limits are defined.
   * The value must not be negative.
   * </pre>
   *
   * <code>map&lt;string, int64&gt; metric_costs = 2;</code>
   */

  public boolean containsMetricCosts(
      String key) {
    if (key == null) { throw new NullPointerException(); }
    return internalGetMetricCosts().getMap().containsKey(key);
  }
  /**
   * Use {@link #getMetricCostsMap()} instead.
   */
  @Deprecated
  public java.util.Map<String, Long> getMetricCosts() {
    return getMetricCostsMap();
  }
  /**
   * <pre>
   * Metrics to update when the selected methods are called, and the associated
   * cost applied to each metric.
   * The key of the map is the metric name, and the values are the amount
   * increased for the metric against which the quota limits are defined.
   * The value must not be negative.
   * </pre>
   *
   * <code>map&lt;string, int64&gt; metric_costs = 2;</code>
   */

  public java.util.Map<String, Long> getMetricCostsMap() {
    return internalGetMetricCosts().getMap();
  }
  /**
   * <pre>
   * Metrics to update when the selected methods are called, and the associated
   * cost applied to each metric.
   * The key of the map is the metric name, and the values are the amount
   * increased for the metric against which the quota limits are defined.
   * The value must not be negative.
   * </pre>
   *
   * <code>map&lt;string, int64&gt; metric_costs = 2;</code>
   */

  public long getMetricCostsOrDefault(
      String key,
      long defaultValue) {
    if (key == null) { throw new NullPointerException(); }
    java.util.Map<String, Long> map =
        internalGetMetricCosts().getMap();
    return map.containsKey(key) ? map.get(key) : defaultValue;
  }
  /**
   * <pre>
   * Metrics to update when the selected methods are called, and the associated
   * cost applied to each metric.
   * The key of the map is the metric name, and the values are the amount
   * increased for the metric against which the quota limits are defined.
   * The value must not be negative.
   * </pre>
   *
   * <code>map&lt;string, int64&gt; metric_costs = 2;</code>
   */

  public long getMetricCostsOrThrow(
      String key) {
    if (key == null) { throw new NullPointerException(); }
    java.util.Map<String, Long> map =
        internalGetMetricCosts().getMap();
    if (!map.containsKey(key)) {
      throw new IllegalArgumentException();
    }
    return map.get(key);
  }

  private byte memoizedIsInitialized = -1;
  @Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getSelectorBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, selector_);
    }
    com.google.protobuf.GeneratedMessageV3
      .serializeStringMapTo(
        output,
        internalGetMetricCosts(),
        MetricCostsDefaultEntryHolder.defaultEntry,
        2);
    unknownFields.writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getSelectorBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, selector_);
    }
    for (java.util.Map.Entry<String, Long> entry
         : internalGetMetricCosts().getMap().entrySet()) {
      com.google.protobuf.MapEntry<String, Long>
      metricCosts__ = MetricCostsDefaultEntryHolder.defaultEntry.newBuilderForType()
          .setKey(entry.getKey())
          .setValue(entry.getValue())
          .build();
      size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(2, metricCosts__);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof MetricRule)) {
      return super.equals(obj);
    }
    MetricRule other = (MetricRule) obj;

    if (!getSelector()
        .equals(other.getSelector())) return false;
    if (!internalGetMetricCosts().equals(
        other.internalGetMetricCosts())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + SELECTOR_FIELD_NUMBER;
    hash = (53 * hash) + getSelector().hashCode();
    if (!internalGetMetricCosts().getMap().isEmpty()) {
      hash = (37 * hash) + METRIC_COSTS_FIELD_NUMBER;
      hash = (53 * hash) + internalGetMetricCosts().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static MetricRule parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static MetricRule parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static MetricRule parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static MetricRule parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static MetricRule parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static MetricRule parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static MetricRule parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static MetricRule parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static MetricRule parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static MetricRule parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static MetricRule parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static MetricRule parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(MetricRule prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @Override
  protected Builder newBuilderForType(
      BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * Bind API methods to metrics. Binding a method to a metric causes that
   * metric's configured quota behaviors to apply to the method call.
   * </pre>
   *
   * Protobuf type {@code google.api.MetricRule}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:google.api.MetricRule)
      com.google.api.MetricRuleOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.google.api.QuotaProto.internal_static_google_api_MetricRule_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMapField(
        int number) {
      switch (number) {
        case 2:
          return internalGetMetricCosts();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMutableMapField(
        int number) {
      switch (number) {
        case 2:
          return internalGetMutableMetricCosts();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.google.api.QuotaProto.internal_static_google_api_MetricRule_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              MetricRule.class, Builder.class);
    }

    // Construct using com.google.api.MetricRule.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @Override
    public Builder clear() {
      super.clear();
      selector_ = "";

      internalGetMutableMetricCosts().clear();
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.google.api.QuotaProto.internal_static_google_api_MetricRule_descriptor;
    }

    @Override
    public MetricRule getDefaultInstanceForType() {
      return MetricRule.getDefaultInstance();
    }

    @Override
    public MetricRule build() {
      MetricRule result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public MetricRule buildPartial() {
      MetricRule result = new MetricRule(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.selector_ = selector_;
      result.metricCosts_ = internalGetMetricCosts();
      result.metricCosts_.makeImmutable();
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    @Override
    public Builder clone() {
      return super.clone();
    }
    @Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return super.setField(field, value);
    }
    @Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return super.addRepeatedField(field, value);
    }
    @Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof MetricRule) {
        return mergeFrom((MetricRule)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(MetricRule other) {
      if (other == MetricRule.getDefaultInstance()) return this;
      if (!other.getSelector().isEmpty()) {
        selector_ = other.selector_;
        onChanged();
      }
      internalGetMutableMetricCosts().mergeFrom(
          other.internalGetMetricCosts());
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @Override
    public final boolean isInitialized() {
      return true;
    }

    @Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      MetricRule parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (MetricRule) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private Object selector_ = "";
    /**
     * <pre>
     * Selects the methods to which this rule applies.
     * Refer to [selector][google.api.DocumentationRule.selector] for syntax details.
     * </pre>
     *
     * <code>string selector = 1;</code>
     */
    public String getSelector() {
      Object ref = selector_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        selector_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <pre>
     * Selects the methods to which this rule applies.
     * Refer to [selector][google.api.DocumentationRule.selector] for syntax details.
     * </pre>
     *
     * <code>string selector = 1;</code>
     */
    public com.google.protobuf.ByteString
        getSelectorBytes() {
      Object ref = selector_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        selector_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * Selects the methods to which this rule applies.
     * Refer to [selector][google.api.DocumentationRule.selector] for syntax details.
     * </pre>
     *
     * <code>string selector = 1;</code>
     */
    public Builder setSelector(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      selector_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Selects the methods to which this rule applies.
     * Refer to [selector][google.api.DocumentationRule.selector] for syntax details.
     * </pre>
     *
     * <code>string selector = 1;</code>
     */
    public Builder clearSelector() {
      
      selector_ = getDefaultInstance().getSelector();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Selects the methods to which this rule applies.
     * Refer to [selector][google.api.DocumentationRule.selector] for syntax details.
     * </pre>
     *
     * <code>string selector = 1;</code>
     */
    public Builder setSelectorBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      selector_ = value;
      onChanged();
      return this;
    }

    private com.google.protobuf.MapField<
        String, Long> metricCosts_;
    private com.google.protobuf.MapField<String, Long>
    internalGetMetricCosts() {
      if (metricCosts_ == null) {
        return com.google.protobuf.MapField.emptyMapField(
            MetricCostsDefaultEntryHolder.defaultEntry);
      }
      return metricCosts_;
    }
    private com.google.protobuf.MapField<String, Long>
    internalGetMutableMetricCosts() {
      onChanged();;
      if (metricCosts_ == null) {
        metricCosts_ = com.google.protobuf.MapField.newMapField(
            MetricCostsDefaultEntryHolder.defaultEntry);
      }
      if (!metricCosts_.isMutable()) {
        metricCosts_ = metricCosts_.copy();
      }
      return metricCosts_;
    }

    public int getMetricCostsCount() {
      return internalGetMetricCosts().getMap().size();
    }
    /**
     * <pre>
     * Metrics to update when the selected methods are called, and the associated
     * cost applied to each metric.
     * The key of the map is the metric name, and the values are the amount
     * increased for the metric against which the quota limits are defined.
     * The value must not be negative.
     * </pre>
     *
     * <code>map&lt;string, int64&gt; metric_costs = 2;</code>
     */

    public boolean containsMetricCosts(
        String key) {
      if (key == null) { throw new NullPointerException(); }
      return internalGetMetricCosts().getMap().containsKey(key);
    }
    /**
     * Use {@link #getMetricCostsMap()} instead.
     */
    @Deprecated
    public java.util.Map<String, Long> getMetricCosts() {
      return getMetricCostsMap();
    }
    /**
     * <pre>
     * Metrics to update when the selected methods are called, and the associated
     * cost applied to each metric.
     * The key of the map is the metric name, and the values are the amount
     * increased for the metric against which the quota limits are defined.
     * The value must not be negative.
     * </pre>
     *
     * <code>map&lt;string, int64&gt; metric_costs = 2;</code>
     */

    public java.util.Map<String, Long> getMetricCostsMap() {
      return internalGetMetricCosts().getMap();
    }
    /**
     * <pre>
     * Metrics to update when the selected methods are called, and the associated
     * cost applied to each metric.
     * The key of the map is the metric name, and the values are the amount
     * increased for the metric against which the quota limits are defined.
     * The value must not be negative.
     * </pre>
     *
     * <code>map&lt;string, int64&gt; metric_costs = 2;</code>
     */

    public long getMetricCostsOrDefault(
        String key,
        long defaultValue) {
      if (key == null) { throw new NullPointerException(); }
      java.util.Map<String, Long> map =
          internalGetMetricCosts().getMap();
      return map.containsKey(key) ? map.get(key) : defaultValue;
    }
    /**
     * <pre>
     * Metrics to update when the selected methods are called, and the associated
     * cost applied to each metric.
     * The key of the map is the metric name, and the values are the amount
     * increased for the metric against which the quota limits are defined.
     * The value must not be negative.
     * </pre>
     *
     * <code>map&lt;string, int64&gt; metric_costs = 2;</code>
     */

    public long getMetricCostsOrThrow(
        String key) {
      if (key == null) { throw new NullPointerException(); }
      java.util.Map<String, Long> map =
          internalGetMetricCosts().getMap();
      if (!map.containsKey(key)) {
        throw new IllegalArgumentException();
      }
      return map.get(key);
    }

    public Builder clearMetricCosts() {
      internalGetMutableMetricCosts().getMutableMap()
          .clear();
      return this;
    }
    /**
     * <pre>
     * Metrics to update when the selected methods are called, and the associated
     * cost applied to each metric.
     * The key of the map is the metric name, and the values are the amount
     * increased for the metric against which the quota limits are defined.
     * The value must not be negative.
     * </pre>
     *
     * <code>map&lt;string, int64&gt; metric_costs = 2;</code>
     */

    public Builder removeMetricCosts(
        String key) {
      if (key == null) { throw new NullPointerException(); }
      internalGetMutableMetricCosts().getMutableMap()
          .remove(key);
      return this;
    }
    /**
     * Use alternate mutation accessors instead.
     */
    @Deprecated
    public java.util.Map<String, Long>
    getMutableMetricCosts() {
      return internalGetMutableMetricCosts().getMutableMap();
    }
    /**
     * <pre>
     * Metrics to update when the selected methods are called, and the associated
     * cost applied to each metric.
     * The key of the map is the metric name, and the values are the amount
     * increased for the metric against which the quota limits are defined.
     * The value must not be negative.
     * </pre>
     *
     * <code>map&lt;string, int64&gt; metric_costs = 2;</code>
     */
    public Builder putMetricCosts(
        String key,
        long value) {
      if (key == null) { throw new NullPointerException(); }
      
      internalGetMutableMetricCosts().getMutableMap()
          .put(key, value);
      return this;
    }
    /**
     * <pre>
     * Metrics to update when the selected methods are called, and the associated
     * cost applied to each metric.
     * The key of the map is the metric name, and the values are the amount
     * increased for the metric against which the quota limits are defined.
     * The value must not be negative.
     * </pre>
     *
     * <code>map&lt;string, int64&gt; metric_costs = 2;</code>
     */

    public Builder putAllMetricCosts(
        java.util.Map<String, Long> values) {
      internalGetMutableMetricCosts().getMutableMap()
          .putAll(values);
      return this;
    }
    @Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:google.api.MetricRule)
  }

  // @@protoc_insertion_point(class_scope:google.api.MetricRule)
  private static final MetricRule DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new MetricRule();
  }

  public static MetricRule getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<MetricRule>
      PARSER = new com.google.protobuf.AbstractParser<MetricRule>() {
    @Override
    public MetricRule parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new MetricRule(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MetricRule> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<MetricRule> getParserForType() {
    return PARSER;
  }

  @Override
  public MetricRule getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

