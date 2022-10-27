<?xml version="1.0" encoding="UTF-8"?>
<TestSuiteEntity>
   <description></description>
   <name>MTP_QA</name>
   <tag></tag>
   <isRerun>false</isRerun>
   <mailRecipient></mailRecipient>
   <numberOfRerun>3</numberOfRerun>
   <pageLoadTimeout>30</pageLoadTimeout>
   <pageLoadTimeoutDefault>true</pageLoadTimeoutDefault>
   <rerunFailedTestCasesOnly>false</rerunFailedTestCasesOnly>
   <rerunImmediately>true</rerunImmediately>
   <testSuiteGuid>61a2c20d-e00c-4821-91ca-1b7c4908960c</testSuiteGuid>
   <testCaseLink>
      <guid>c34a23a7-c1b9-496e-9e67-0cc0e676c99d</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <iterationNameVariable>
         <defaultValue>findTestData('targetAssoc').getValue('suffix_Url', 1)</defaultValue>
         <description></description>
         <id>be97a4a6-91db-41fe-82b8-501f029de449</id>
         <masked>false</masked>
         <name>sUrl</name>
      </iterationNameVariable>
      <testCaseId>Test Cases/MTP_TestCases/TC01_MTP_DataValidation_POC_TargetAssoc</testCaseId>
      <testDataLink>
         <combinationType>ONE</combinationType>
         <id>5d969e3f-db4c-449e-8327-fa7d9e871c15</id>
         <iterationEntity>
            <iterationType>ALL</iterationType>
            <value></value>
         </iterationEntity>
         <testDataId>Data Files/targetAssoc</testDataId>
      </testDataLink>
      <usingDataBindingAtTestSuiteLevel>true</usingDataBindingAtTestSuiteLevel>
      <variableLink>
         <testDataLinkId>5d969e3f-db4c-449e-8327-fa7d9e871c15</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>suffix_Url</value>
         <variableId>be97a4a6-91db-41fe-82b8-501f029de449</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>5d969e3f-db4c-449e-8327-fa7d9e871c15</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>target_ID</value>
         <variableId>b71d204a-3b78-4bd2-9eeb-45d0219e1bcf</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>5d969e3f-db4c-449e-8327-fa7d9e871c15</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>target_Name</value>
         <variableId>5f828572-0003-409b-acf7-50a7fc71ab24</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>5d969e3f-db4c-449e-8327-fa7d9e871c15</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>PMTLcode</value>
         <variableId>3f3bf3da-3a37-4c3e-bc91-961c62424b18</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>5d969e3f-db4c-449e-8327-fa7d9e871c15</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>diseaseCount</value>
         <variableId>c4a4d914-23c2-40ee-843d-39e71618c4db</variableId>
      </variableLink>
   </testCaseLink>
</TestSuiteEntity>
