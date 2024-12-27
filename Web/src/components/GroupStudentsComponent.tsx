import {ActionIcon, Badge, Box, Button, Group, Loader, Notification, Table, Text} from '@mantine/core';
import {IconTrash} from "@tabler/icons-react";
import {stringToColour} from "../stringToColour.ts";
import {useMutation, useQuery} from "@tanstack/react-query";
import {fetchGroupInfo} from "../api/service.ts";
import {Link, useParams} from "react-router-dom";
import {axiosConfig} from "../../axios.ts";
import {queryClient} from "../api/query-client.ts";


export default function GroupStudentsComponent() {
    const {id} = useParams();
    const deleteStudent = async (id_student: number) => {
        return (await axiosConfig.delete(`/api/student-sub/delete?id=${id_student}`)).data;
    }
    const {mutate} = useMutation(deleteStudent, {
        onSuccess: () => {
            queryClient.invalidateQueries(["group-info"]);
        },
    });

    const handleDelete = (id:number) => {
        mutate(id);
    };
    const { status, data, error } = useQuery(["group-info", id], () => fetchGroupInfo(Number(id)));

    if (status === "loading") {
        return (
            <Box style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100%' }}>
                <Loader size="lg" />
            </Box>
        );
    } else if (status === "error") {
        return (
            <Notification color="red" title="Error loading">
                {error.message || 'An unknown error occurred.'}
            </Notification>
        );
    }

    const rows_tests = data.data.activeTests.length > 0 ?
        data.data.activeTests.map((item) => (
            <Table.Tr key={item.testName}>
                <Table.Td ta={"left"}>
                    <Group gap="sm">
                        <Link to={`/tests/${item.id}`}>
                            <Button variant={"default"} fz="sm" fw={500}>
                                {item.testName}
                            </Button>
                        </Link>
                    </Group>
                </Table.Td>

                <Table.Td ta={"center"}>
                    <Badge color={stringToColour(item.disciplineName)}>
                        {item.disciplineName}
                    </Badge>
                </Table.Td>
                <Table.Td ta={"right"}>
                    <Text size="sm">
                        {item.expiresTime}
                    </Text>
                </Table.Td>
            </Table.Tr>
        )) :
        <Table.Tr>
            <Table.Td colSpan={3} ta={"center"}>Нет активных тестов для этой группы.</Table.Td>
        </Table.Tr>;

    const rows_students = data.data.students.length > 0 ?
        data.data.students.map((item) => (
            <Table.Tr key={item.name}>
                <Table.Td>
                    <Group gap="sm">
                        <Text fz="sm" fw={500}>
                            {item.firstName + " " + item.lastName}
                        </Text>
                    </Group>
                </Table.Td>
                <Table.Td>
                    <Group gap="sm">
                        <Text fz="sm" fw={500}>
                            {item.chatId}
                        </Text>
                    </Group>
                </Table.Td>
                <Table.Td>
                    <Group gap={0} justify="flex-end">
                        <ActionIcon onClick={() => handleDelete(Number(item.subs[0]))} variant="subtle" color="red">
                            <IconTrash size={16} stroke={1.5} />
                        </ActionIcon>
                    </Group>
                </Table.Td>
            </Table.Tr>
        )) :
        <Table.Tr>
            <Table.Td colSpan={3} ta={"center"}>Нет студентов в этой группе.</Table.Td>
        </Table.Tr>;

    return (
        <>
            <Text size="lg" c={"blue"} ta={"center"} p={10}>Активные Тесты группы {data.data.name}</Text>
            <Table.ScrollContainer minWidth={800}>
                <Table verticalSpacing="sm">
                    <Table.Thead>
                        <Table.Tr>
                            <Table.Th ta={"left"}>Название Теста</Table.Th>
                            <Table.Th ta={"center"}>Дисциплина</Table.Th>
                            <Table.Th ta={"right"}>Окончание</Table.Th>
                        </Table.Tr>
                    </Table.Thead>
                    <Table.Tbody>{rows_tests}</Table.Tbody>
                </Table>
                <Text size="lg" c={"blue"} ta={"center"} p={10}>Студенты группы {data.data.name}</Text>
                <Table verticalSpacing="sm">
                    <Table.Thead>
                        <Table.Tr>
                            <Table.Th>ФИО</Table.Th>
                            <Table.Th>Телеграм тег</Table.Th>
                        </Table.Tr>
                    </Table.Thead>
                    <Table.Tbody>{rows_students}</Table.Tbody>
                </Table>
            </Table.ScrollContainer>
        </>
    );
}