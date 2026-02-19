# Retrieval script updated to use only project_id and suite_id
import requests
from config import API_ENDPOINT

def get_cases(project_id, suite_id, headers):
    url = API_ENDPOINT.format(project_id=project_id, suite_id=suite_id)
    response = requests.get(url, headers=headers)
    if response.status_code == 200:
        return response.json()
    else:
        raise Exception(f'Failed to retrieve cases: {response.status_code} {response.text}')

if __name__ == '__main__':
    import os
    project_id = os.environ.get('TESTRAIL_PROJECT_ID')
    suite_id = os.environ.get('TESTRAIL_SUITE_ID')
    headers = {'Content-Type': 'application/json', 'Authorization': os.environ.get('TESTRAIL_AUTH')}
    cases = get_cases(project_id, suite_id, headers)
    print(cases)
