angular.module('SmartschoolApp').constant('constants',{
  authTokenKey: 'X-Auth-Token',
  url: 'http://localhost:8080/smartschool-ws/',
  loginUri: 'api/login/',
  professorsUri: 'api/professors',
  disciplinesUri: 'api/disciplines',
  status: {
      WARNING: "warning",
      SUCCESS: "success",
      DANGER: "danger",
      INFO: "info",
      LOADING: "loading"
  },
  message:{
      NOT_LOADED: "Não carregado",
      LOADING: "Carregando registros",
      ERROR: "Erro ao carregar registros",
      EMPTY:"Nenhum registro encontrado",
      CONNECTION_ERROR: "Não foi possível conectar-se ao servidor",
      REGISTRY_SAVED: "Registro salvo com sucesso!",
      REGISTRY_UPDATED: "Registro atualizado com sucesso!",
  },
  table:{
      FIRST_PAGE: 1,
      COUNTS_PER_PAGE: 10,
      SORTING: 'desc'
  }
});
