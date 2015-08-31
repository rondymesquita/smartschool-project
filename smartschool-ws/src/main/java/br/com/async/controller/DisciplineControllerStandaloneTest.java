package br.com.async.controller;


/**
 * Created by rondymesquita on 30/08/2015
 *
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {WebConfig.class})
//@WebAppConfiguration
public class DisciplineControllerStandaloneTest{

//	@Mock
//	private DisciplineApplication disciplineApplication;
//	
//	@InjectMocks
//	private DisciplineController disciplineController = new DisciplineController();
//	
//	private MockMvc mockMvc;
//	
//	@Before
//	public void before(){
//		DisciplineHelper.cleanup();
//		
//	}
//	
//	@After
//	public void after(){
//		DisciplineHelper.cleanup();
//	}
//	
//	@Test
//    public void shouldReturnErrorWhenDeleteTheDisciplineAsManagerTest() throws Exception {
//		MockitoAnnotations.initMocks(this);
//		mockMvc = MockMvcBuilders.standaloneSetup(disciplineController).build();
//		
//		Discipline discipline = DisciplineHelper.saveBasic();
//		
//		Mockito.when(disciplineApplication.delete(Mockito.any(Discipline.class))).thenReturn(false);
//		
//		ResponseData responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);
//		String jsonReponse = JsonUtils.toJson(responseData, JsonUtils.BLANK_STRING_REGEX);
//
//		mockMvc.perform(
//				delete("/api/disciplines/{id}", discipline.getCode())
//				)
//		.andExpect(status().isOk())
//		.andExpect(content().string(jsonReponse));
//	}
//	
}
