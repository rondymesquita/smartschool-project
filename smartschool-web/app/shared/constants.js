angular.module('SmartschoolApp').constant('constants',{
  authTokenKey: 'X-Auth-Token',
  url: 'http://localhost:9090/smartschool-ws/',
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
      NOT_LOADED: "Nao carregado",
      LOADING: "Carregando registros",
      ERROR: "Erro ao carregar registros",
      EMPTY:"Nenhum registro encontrado",
      CONNECTION_ERROR: "Não foi possível conectar-se ao servidor"
  }
});
